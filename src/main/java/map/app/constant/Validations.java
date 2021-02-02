package map.app.constant;

public final class Validations {
    
    private Validations(){}
    
    //REGEX
    public static final String URL_REGEX = "^(http://|https://).*";
    public static final String TITLE_REGEX = "^\\s*[A-Z]\\w*\\s*(a|an|the|and|but|or|on|in|with|([A-Z]\\w*)\\s*)*$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,30}$";
    
    //Violations DTO/Entity
    public static final String URL_VIOLATION = "Invalid URL.";
    public static final String TRAILER_VIOLATION = "Invalid trailer.";
    public static final String SIZE_VIOLATION = "Size can not be negative.";
    public static final String PRICE_VIOLATION = "Price can not be negative.";
    public static final String TITLE_VIOLATION = "Title is not valid.";
    public static final String EMAIL_VIOLATION = "Email is not valid.";
    public static final String PASSWORD_VIOLATION = "Password is not valid.";
    public static final String NAME_VIOLATION = "Name must not be null.";
    public static final String NAME_LENGTH_VIOLATION = "Name is too long.";
    public static final String NO_ENTITY = "No such entity.";


    //Violations Service
    public static final String PASSWORD_MISMATCH = "Password doesn't match.";
    public static final String ADD_GAME_VIOLATION = "Cannot add game.%nGame with title %s already exists.%n";
    public static final String USER_NOT_ADMIN = "User is not an admin.";
    public static final String INCORRECT_NAME_PASSWORD = "Incorrect username/password";
    public static final String CANNOT_LOG_OUT_NO_USER_LOGGED = "Cannot log out. No user was logged in.";
    public static final String CANNOT_LOGIN_WHEN_OTHER_IS_LOGGED ="Cannot login while another used is logged in.";
    public static final String NO_USER_LOGGED = "No user is logged in.";
    public static final String NO_VALUES_TO_EDIT = "No values entered for game edit.";
    public static final String NO_COMMAND = "No such command.";
    public static final String NO_VALUES = "No values entered.";
    public static final String NO_SUCH_GAME_TITLE = "Game with title %s does not exist.%n";
    public static final String NO_SUCH_GAME_ID = "Game with ID %d does not exist.%n";
    public static final String CANNOT_ADD_GAME_TO_CART = "Cannot add %s to cart. Item already owned.%n";
    public static final String ALREADY_ADDED_GAMES = "Game already added in cart.";
}
