package teamchalkling.chalkling.business_logic_layer;

import teamchalkling.chalkling.database_layer.UserDAC;

public class LoginController {

  private UserDAC userDAC;

  public LoginController(UserDAC userDAC) {
    this.userDAC = userDAC;
  }

  /**
   * return true if username and password are valid
   * @param username the username of the user
   * @param password the password of the user
   */
  public boolean check(String username, String password) {
    this.readUserData();
    boolean isLogin = userDAC.getUserService().canLogin(username, password);
    if (isLogin) {
      userDAC.getUserService().setCurrentUser(username);
    }
    return isLogin;
  }

  private void readUserData() {
    // read all users by UserDAC
    this.userDAC.read();
  }

}
