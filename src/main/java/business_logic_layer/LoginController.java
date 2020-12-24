package business_logic_layer;

public class LoginController {

  private static LoginController instance = new LoginController();

  public static LoginController getInstance() {
    return instance;
  }

  public boolean check(String username, String password) {
    return false;
  }

}
