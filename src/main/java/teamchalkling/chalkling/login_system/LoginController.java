package teamchalkling.chalkling.login_system;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import teamchalkling.chalkling.user_system.*;

// TODO: Combine LoginController and LoginWebAPI. Might need to change userService and its test cases
//  accordingly.
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
    String[] result = createSaltAndHash(password);
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

  private String[] createSaltAndHash(String input) {
    String salt = BCrypt.gensalt(10);
    String hash = BCrypt.hashpw(input, salt);
    return new String[]{salt, hash};
  }

}
