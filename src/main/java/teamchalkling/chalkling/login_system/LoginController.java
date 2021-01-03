package teamchalkling.chalkling.login_system;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teamchalkling.chalkling.user_system.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

  private final UserService userService;

  @Inject
  public LoginController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Return true if username and password of given loginJSON matches with database
   * @param userJSON input from user
   * @return true if username and password are valid
   */
  @PostMapping(value = "/api/login", consumes = "application/json", produces = "application/json")
  public StatusJSON verifyLogin(@RequestBody UserJSON userJSON, HttpServletRequest request) {

    // check if user exists
    if (userService.userExists(userJSON.getUsername())) {

      // username exist, read salt, generate givenHash
      String salt = userService.getUserSalt(userJSON.getUsername());
      String givenHash = BCrypt.hashpw(userJSON.getPassword(), salt);

      // verify the user account
      if (userService.canLogin(userJSON.getUsername(), salt, givenHash)) {

        // TODO: Should store JWTs in the session rather than just the username.
        userService.setCurrentUser(request.getSession(), userJSON.getUsername());

        return new StatusJSON(true);
      }
    }
    return new StatusJSON(false);
  }

  @PostMapping(value = "/api/signup", consumes = "application/json", produces = "application/json")
  public SignUpJSON addUser(@RequestBody UserJSON userJSON){

    // verify contents
    if (userJSON.getUsername().trim().isEmpty()) {
      return new SignUpJSON(LoginPrompt.EMPTY_USERNAME);
    } else if (userJSON.getPassword().trim().isEmpty()) {
      return new SignUpJSON(LoginPrompt.EMPTY_PASSWORD);
    } else if (!StringUtils.isAlphanumeric(userJSON.getUsername())) {
      return new SignUpJSON(LoginPrompt.NOT_ALPHANUM);
    } else if (userService.userExists(userJSON.getUsername())) {
      return new SignUpJSON(LoginPrompt.USER_EXIST);
    }

    // hash password
    String[] result = createSaltAndHash(userJSON.getPassword());
    String salt = result[0];
    String hash = result[1];

    // add user to system and return success
    userService.addUser(userJSON.getUsername(), salt, hash);
    return new SignUpJSON(LoginPrompt.SUCCESS);
  }

  private String[] createSaltAndHash(String input) {
    String salt = BCrypt.gensalt(10);
    String hash = BCrypt.hashpw(input, salt);
    return new String[]{salt, hash};
  }
}
