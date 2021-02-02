package map.app.service.contract;

import map.app.domain.dto.GameAddDto;
import map.app.domain.dto.GameEditDto;
import map.app.domain.entity.Game;

public interface GameService {

    void addGame(GameAddDto gameAddDto);

    Game getGameByTitle(String title);

    GameEditDto getGameDtoFromRepo(Integer id);

    void updateGame(GameEditDto gameEditDto);

    Game getGameById(Integer id);

    void deleteGame(Integer id);

    void displayAllGames();

    void displayDetails(String title);
}
