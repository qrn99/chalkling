package teamchalkling.chalkling.login_system;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teamchalkling.chalkling.user_system.*;

import javax.inject.Inject;

@RestController
public class LoginController {

  private final UserService userService;

  @Inject
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

  /**
   * Return true if username and password of given loginJSON matches with database
   * @param userJSON input from user
   * @return true if username and password are valid
   */
  @PostMapping(value = "/api/login", consumes = "application/json", produces = "application/json")
  public StatusJSON verifyLogin(@RequestBody UserJSON userJSON) {
    boolean isLogin = this.check(userJSON.getUsername(), userJSON.getPassword());
    return new StatusJSON(isLogin);
  }

  @PostMapping(value = "/api/signup", consumes = "application/json", produces = "application/json")
  public StatusJSON addUser(@RequestBody UserJSON userJSON){
    boolean res = this.addUser(userJSON.getUsername(), userJSON.getPassword());
    return new StatusJSON(res);
  }

  private String[] createSaltAndHash(String input) {
    String salt = BCrypt.gensalt(10);
    String hash = BCrypt.hashpw(input, salt);
    return new String[]{salt, hash};
  }

}
