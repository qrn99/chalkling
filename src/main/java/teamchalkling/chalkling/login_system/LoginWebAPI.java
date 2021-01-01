package teamchalkling.chalkling.login_system;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;


@RestController
public class LoginWebAPI {

    private final LoginController loginController;

    @Inject
    public LoginWebAPI(LoginController loginController){
        this.loginController = loginController;
    }

    /**
     * Return true if username and password of given loginJSON matches with database
     * @param userJSON input from user
     * @return true if username and password are valid
     */
    @PostMapping(value = "/api/login", consumes = "application/json", produces = "application/json")
    public StatusJSON verifyLogin(@RequestBody UserJSON userJSON) {
        boolean isLogin = loginController.check(userJSON.getUsername(), userJSON.getPassword());
        return new StatusJSON(isLogin);

    }

    @PostMapping(value = "/api/signup", consumes = "application/json", produces = "application/json")
    public StatusJSON addUser(@RequestBody UserJSON userJSON){
        boolean res = loginController.addUser(userJSON.getUsername(), userJSON.getPassword());
        return new StatusJSON(res);
    }

}