package map.app.constant;

public final class Messages {

    private Messages() {
    }

    //DTO class names
    public static final String REGISTER_DTO = "UserRegisterDto";
    public static final String LOGIN_DTO = "UserLoginDto";
    public static final String ADD_GAME_DTO = "GameAddDto";
    public static final String ADD_ITEM = "AddItem";
    public static final String REMOVE_ITEM = "RemoveItem";
    public static final String BUY_ITEM = "BuyItem";

    //Messages
    public static final String ENTER_COMMAND = "Please enter command.";
    public static final String GAME_INSERTED = "Successfully added %s to the database.%n";
    public static final String LOGGED_IN = "Successfully logged in %s.%n";
    public static final String LOGGED_OUT = "User %s successfully logged out.%n";
    public static final String USER_REGISTERED = "Successfully registered user %s.%n";
    public static final String GAME_DELETED = "Successfully deleted game %s.%n";
    public static final String GAME_REMOVED = "%s removed from cart.%n";
    public static final String GAME_ADDED = "%s added to cart.%n";
    public static final String CART_EMPTY = "No items in cart.";
    public static final String GAMES_BOUGHT = "Successfully bought games:";
    public static final String DISPLAY_DETAILS = "Title: %s%nPrice: %s%nDescription: %s%nRelease date: %s%n";

    //Inputs
    public static final String REGISTER = "RegisterUser";
    public static final String LOGIN = "LoginUser";
    public static final String LOGOUT = "Logout";
    public static final String ADD_GAME = "AddGame";
    public static final String EDIT_GAME = "EditGame";
    public static final String DELETE_GAME = "DeleteGame";
    public static final String DISPLAY_GAMES = "AllGames";
    public static final String DETAIL_GAME = "DetailGame";
    public static final String OWNED_GAMES = "OwnedGames";
    public static final String EXIT = "exit";
}
