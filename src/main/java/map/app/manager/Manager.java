package map.app.manager;

import map.app.domain.dto.GameAddDto;
import map.app.domain.dto.GameEditDto;
import map.app.domain.dto.UserLoginDto;
import map.app.domain.dto.UserRegisterDto;
import map.app.service.contract.GameService;
import map.app.service.contract.ShoppingCart;
import map.app.service.contract.UserService;
import map.app.util.contract.Reader;
import map.app.util.contract.DefineAndValidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static map.app.constant.Messages.*;
import static map.app.constant.Validations.*;

@Component
public class Manager implements Runnable {

    private final Reader reader;
    private final UserService userService;
    private final GameService gameService;
    private final ShoppingCart shoppingCart;
    private final DefineAndValidateEntity defineAndValidateEntity;

    @Autowired
    public Manager(Reader reader, UserService userService,
                   GameService gameService, ShoppingCart shoppingCart,
                   DefineAndValidateEntity defineAndValidateEntity) {
        this.reader = reader;
        this.userService = userService;
        this.gameService = gameService;
        this.shoppingCart = shoppingCart;
        this.defineAndValidateEntity = defineAndValidateEntity;
    }

    @Override
    public void run() {
        System.out.println(ENTER_COMMAND);
        while (true) {
            String[] input = this.reader.read().split("\\|");
            if (input[0].toLowerCase().equals(EXIT)) break;

            try {
                switch (input[0]) {

                    case REGISTER -> {
                        if (!input[2].equals(input[3])) {
                            System.out.println(PASSWORD_MISMATCH);
                            break;
                        }
                        UserRegisterDto userRegisterDto =
                                this.defineAndValidateEntity.execute(UserRegisterDto.class, input);
                        if (userRegisterDto == null) break;
                        this.userService.registerUser(userRegisterDto);
                    }

                    case LOGIN -> {
                        UserLoginDto userLoginDto =
                                this.defineAndValidateEntity.execute(UserLoginDto.class, input);
                        if (userLoginDto == null) break;
                        this.userService.loginUser(userLoginDto);
                        this.shoppingCart.setAnUser(this.userService.getUserDto());
                    }

                    case LOGOUT -> {
                        this.userService.logout();
                        this.shoppingCart.clearShoppingCart();
                    }

                    case ADD_GAME -> {
                        if (!this.userService.checkIfAdmin()) break;
                        GameAddDto gameAddDto =
                                this.defineAndValidateEntity.execute(GameAddDto.class, input);
                        if (gameAddDto == null) break;
                        this.gameService.addGame(gameAddDto);
                    }

                    case EDIT_GAME -> {
                        if (!this.userService.checkIfAdmin()) break;
                        if (input[2] == null) {
                            System.out.println(NO_VALUES_TO_EDIT);
                            break;
                        }
                        GameEditDto gameEditDto =
                                this.defineAndValidateEntity
                                        .editGameValues(this.gameService
                                                .getGameDtoFromRepo(Integer.valueOf(input[1])), input);
                        this.gameService.updateGame(gameEditDto);
                    }
                    case DELETE_GAME -> {
                        if (!this.userService.checkIfAdmin()) break;
                        this.gameService.deleteGame(Integer.valueOf(input[1]));
                    }

                    case ADD_ITEM -> {
                        if (!this.userService.checkIfLoggedIn()) break;
                        this.shoppingCart.addAnItemToCart(input[1]);
                    }
                    case REMOVE_ITEM -> {
                        if (!this.userService.checkIfLoggedIn()) break;
                        this.shoppingCart.removeItemFromCart(input[1]);
                    }
                    case BUY_ITEM -> {
                        if (!this.userService.checkIfLoggedIn()) break;
                        this.shoppingCart.buyItemsFromCart();
                    }

                    case DISPLAY_GAMES -> {
                        this.gameService.displayAllGames();
                    }

                    case DETAIL_GAME -> {
                        this.gameService.displayDetails(input[1]);
                    }

                    case OWNED_GAMES -> {
                        if(!this.userService.checkIfLoggedIn()) break;
                        this.userService.displayUserGames();
                    }

                    default -> System.out.println(NO_COMMAND);

                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(NO_VALUES);
            } catch (NullPointerException e) {
                System.out.println(NO_USER_LOGGED);
            }
        }
    }
}
