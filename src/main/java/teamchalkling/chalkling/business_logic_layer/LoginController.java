package teamchalkling.chalkling.business_logic_layer;

import teamchalkling.chalkling.database_layer.UserDAC;

public class LoginController {

  private UserService userService;
  private UserDAC userDAC;

  public LoginController(UserService userService, UserDAC userDAC) {
    this.userService = userService;
    this.userDAC = userDAC;
  }

  public boolean addUser(String username, String password){
    userService.addUser(username, password);
    return true;
  }

  /**
   * return true if username and password are valid
   * @param username the username of the user
   * @param password the password of the user
   */
  public boolean check(String username, String password) {
    boolean isLogin = userDAC.getUserService().canLogin(username, password);
    if (isLogin) {
      userDAC.getUserService().setCurrentUser(username);
    }
    return isLogin;
  }

  public boolean read(){
    userDAC.read();
    return true;
  }

  public boolean write(){
    userDAC.write();
    return true;
  }

}
