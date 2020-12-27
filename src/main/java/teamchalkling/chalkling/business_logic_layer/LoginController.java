package teamchalkling.chalkling.business_logic_layer;

public class LoginController {

    private boolean loginState;
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Check and save login state in loginState
     * @param username the username of the user
     * @param password the password of the user
     */
    public void check(String username, String password) {
        this.loginState = userService.canLogin(username, password);
    }

    /**
     * Gets the current login state
     * @return true if logged in, false if not logged in
     */
    public boolean getLoginState() {
        return this.loginState;
    }

}

