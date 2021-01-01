package teamchalkling.chalkling.business_logic_layer;

//import org.json.JSONObject;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;


@RestController
public class LoginWebAPI {

    private Boolean isLogin;

    private final LoginController loginController;

    @Inject
    public LoginWebAPI(LoginController loginController){
        this.loginController = loginController;
        isLogin = Boolean.FALSE;
    }

    /**
     * Return true if username and password of given loginJSON matches with database
     * @param userJSON
     * @return true if username and password are valid
     */
    @PostMapping(value = "/api/login", consumes = "application/json", produces = "application/json")
    public StatusJSON verifyLogin(@RequestBody UserJSON userJSON) {
        isLogin = loginController.check(userJSON.getUsername(), userJSON.getPassword());
        return new StatusJSON(isLogin);

    }

    @PostMapping(value = "/api/signup", consumes = "application/json", produces = "application/json")
    public StatusJSON addUser(@RequestBody UserJSON userJSON){
        boolean res = loginController.addUser(userJSON.getUsername(), userJSON.getPassword());
        return new StatusJSON(res);
    }

}