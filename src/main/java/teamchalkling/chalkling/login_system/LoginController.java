package teamchalkling.chalkling.login_system;

import org.apache.commons.lang3.StringUtils;
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

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Add user to userService
   * @param username the username of the user
   * @param password the password of the user
   * @return boolean  True if user does not exist in db and can be added newly, false otherwise
   */
  public LoginPrompt addUserAccount(String username, String password){
    //TODO: syntax validation to not have empty input, alphanumeric, balala
    // return enum error to prompt user

    if (username.trim().isEmpty()) {
      return LoginPrompt.EMPTY_USERNAME;
    } else if (password.trim().isEmpty()) {
      return LoginPrompt.EMPTY_PASSWORD;
    } else if (!StringUtils.isAlphanumeric(username)) {
      return LoginPrompt.NOT_ALPHANUM;
    } else if (userService.userExists(username)) {
      return LoginPrompt.USER_EXIST;
    }

    String[] result = createSaltAndHash(password);
    String salt = result[0];
    String hash = result[1];

    userService.addUser(username, salt, hash);
    return LoginPrompt.SUCCESS;
  }

  /**
   * return true if username and password are valid
   * @param username the username of the user
   * @param password the password of the user
   */
  public boolean check(String username, String password) {
    if (!userService.userExists(username)) {
      // username don't exist
      return false;
    } else {
      // username exist, read salt, generate givenHash
      String salt = userService.getUserSalt(username);
      String givenHash = BCrypt.hashpw(password, salt);
      if (userService.canLogin(username, salt, givenHash)) {
        userService.setCurrentUser(username);
        return true;
      }
      return false;
    }
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
  public SignUpJSON addUser(@RequestBody UserJSON userJSON){
    LoginPrompt res = this.addUserAccount(userJSON.getUsername(), userJSON.getPassword());
    return new SignUpJSON(res);
  }

  private String[] createSaltAndHash(String input) {
    String salt = BCrypt.gensalt(10);
    String hash = BCrypt.hashpw(input, salt);
    return new String[]{salt, hash};
  }

}
