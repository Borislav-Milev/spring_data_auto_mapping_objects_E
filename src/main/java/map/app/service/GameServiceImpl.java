package map.app.service;

import map.app.domain.dto.GameAddDto;
import map.app.domain.dto.GameEditDto;
import map.app.domain.entity.Game;
import map.app.repository.GameRepository;
import map.app.service.contract.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static map.app.constant.Messages.*;
import static map.app.constant.Validations.*;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        Game game = this.getGameByTitle(gameAddDto.getTitle());
        if(game != null){
            System.out.printf(ADD_GAME_VIOLATION, game.getTitle());
            return;
        }
        game = this.modelMapper.map(gameAddDto, Game.class);
        this.gameRepository.saveAndFlush(game);
        System.out.printf(GAME_INSERTED, game.getTitle());
    }

    @Override
    public Game getGameByTitle(String title) {
        return this.gameRepository.findByTitle(title).orElse(null);
    }


    @Override
    public GameEditDto getGameDtoFromRepo(Integer id) {
        Game game = this.getGameById(id);
        return this.modelMapper.map(game, GameEditDto.class);
    }

    @Override
    public void updateGame(GameEditDto gameEditDto) {
        Game game = this.modelMapper.map(gameEditDto, Game.class);
        game.setId(gameEditDto.getId());
        this.gameRepository.saveAndFlush(game);
    }

    @Override
    public Game getGameById(Integer id) {
        return this.gameRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteGame(Integer id) {
        Game game = this.getGameById(id);
        if(game == null){
            System.out.printf(NO_SUCH_GAME_ID, id);
            return;
        }

        this.gameRepository.deleteById(id);
        System.out.printf(GAME_DELETED, game.getTitle());
    }

    @Override
    public void displayAllGames(){
        List<Game> games = this.gameRepository.findAll();
        games.forEach(game -> System.out.printf("%s %s%n", game.getTitle(), game.getPrice()));
    }

    @Override
    public void displayDetails(String title){
        Game game = this.getGameByTitle(title);
        if(game == null){
            System.out.println(NO_SUCH_GAME_TITLE);
            return;
        }
        System.out.printf(DISPLAY_DETAILS,
                game.getTitle(), game.getPrice(),game.getDescription(), game.getReleaseDate());
    }
}
