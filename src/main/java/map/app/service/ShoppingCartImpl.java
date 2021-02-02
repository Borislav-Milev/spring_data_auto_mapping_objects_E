package map.app.service;

import map.app.domain.dto.UserDto;
import map.app.domain.entity.Game;
import map.app.domain.entity.Order;
import map.app.domain.entity.User;
import map.app.repository.OrderRepository;
import map.app.service.contract.GameService;
import map.app.service.contract.ShoppingCart;
import map.app.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static map.app.constant.Messages.*;
import static map.app.constant.Validations.*;

@Component
@Transactional
public class ShoppingCartImpl implements ShoppingCart {

    private final UserService userService;
    private final GameService gameService;
    private final OrderRepository orderRepository;
    private UserDto userDto;
    private final List<Game> games;


    @Autowired
    public ShoppingCartImpl(UserService userService, GameService gameService, OrderRepository orderRepository) {
        this.userService = userService;
        this.gameService = gameService;
        this.orderRepository = orderRepository;
        this.games = new ArrayList<>();
    }

    @Override
    public void setAnUser(UserDto userDto) {
        this.userDto = this.userService.getUserDto();
        if (this.userDto == null) {
            System.out.println(NO_USER_LOGGED);
            return;
        }
        this.userDto = userDto;
    }

    @Override
    public void addAnItemToCart(String title) {
        for (Game game : games) {
            if(game.getTitle().equals(title)){
                System.out.println(ALREADY_ADDED_GAMES);
                return;
            }
        }
        Game game = this.gameService.getGameByTitle(title);
        if (game == null) {
            System.out.printf(NO_SUCH_GAME_TITLE, title);
            return;
        }
        User user = this.userService.findUserByEmail(this.userDto.getEmail());
        for(Game userGame : user.getOwnedGames()){
            if(userGame.getTitle().equals(game.getTitle())) {
                System.out.printf(CANNOT_ADD_GAME_TO_CART, game.getTitle());
                return;
            }
        }
        this.games.add(game);
        System.out.printf(GAME_ADDED, game.getTitle());
    }

    @Override
    public void removeItemFromCart(String title) {
        int count = this.games.size();
        games.removeIf(game -> game.getTitle().equals(title));
        if (count != games.size()) {
            System.out.printf(GAME_REMOVED, title);
        } else {
            System.out.printf(NO_SUCH_GAME_TITLE, title);
        }
    }

    @Override
    public void buyItemsFromCart(){
        if(this.games.size() == 0){
            System.out.println(CART_EMPTY);
            return;
        }
        User user = this.userService.findUserByEmail(this.userDto.getEmail());
        user.getOwnedGames().addAll(this.games);
        this.userService.saveUser(user);
        System.out.println(GAMES_BOUGHT);
        this.games.forEach(game -> System.out.println("\t-" + game.getTitle()));
        addOrder(user);
    }

    private void addOrder(User user){
        Order order = new Order();
        order.setBuyer(user);
        for (Game game : user.getOwnedGames()) {
            Game inputOrderGame = this.gameService.getGameByTitle(game.getTitle());
            for (Game cartGame : games) {
                if(cartGame.getTitle().equals(inputOrderGame.getTitle())){
                    order.getGames().add(inputOrderGame);
                }
            }
        }
        this.orderRepository.saveAndFlush(order);
        this.clearShoppingCart();
    }

    @Override
    public void clearShoppingCart(){
        this.games.clear();
    }
}
