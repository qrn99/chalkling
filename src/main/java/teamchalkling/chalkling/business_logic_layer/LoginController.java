package teamchalkling.chalkling.business_logic_layer;

import teamchalkling.chalkling.database_layer.UserDAC;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController {

  private UserService userService;
  private UserDAC userDAC;

  public LoginController(UserService userService, UserDAC userDAC) {
    this.userService = userService;
    this.userDAC = userDAC;
  }

  /**
   * Add user to userService
   * @param username the username of the user
   * @param password the password of the user
   * @return
   */
  public boolean addUser(String username, String password){
    String[] result = HashPassword.createSaltAndHash(password);
    String salt = result[0];
    String hash = result[1];

    userService.addUser(username, salt, hash);
    return true;
  }

  /**
   * return true if username and password are valid
   * @param username the username of the user
   * @param password the password of the user
   */
  public boolean check(String username, String password) {
    // read salt from database, and transform password to hash
    if (!userService.userExists(username)) {
      // username don't exist
      return false;
    }
    // username exist, read salt, generate givenHash
    String salt = userService.getUserSalt(username);
    String givenHash = BCrypt.hashpw(password, salt);
    // if givenHash matches with actual hash in database, isLogin is true
    boolean isLogin = userService.canLogin(username, salt, givenHash);
    if (isLogin) {
      userService.setCurrentUser(username);
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
