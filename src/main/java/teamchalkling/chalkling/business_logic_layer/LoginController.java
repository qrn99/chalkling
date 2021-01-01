package teamchalkling.chalkling.business_logic_layer;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import teamchalkling.chalkling.jpa.user.*;

@Controller
public class LoginController {

  private UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Add user to userService
   * @param username the username of the user
   * @param password the password of the user
   * @return boolean  True if user does not exist in db and can be added newly, false otherwise
   */
  public boolean addUser(String username, String password){
    String[] result = HashPassword.createSaltAndHash(password);
    String salt = result[0];
    String hash = result[1];

    if (!userService.userExists(username)){
      userService.addUser(username, salt, hash);
      return true;
    }
    return false;
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

}
