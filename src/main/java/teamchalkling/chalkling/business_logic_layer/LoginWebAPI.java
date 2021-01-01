package teamchalkling.chalkling.business_logic_layer;

//import org.json.JSONObject;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teamchalkling.chalkling.database_layer.UserDAC;


@RestController

public class LoginWebAPI {

    private Boolean isLogin;
    private final LoginController loginController;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    public LoginWebAPI(){
        UserService userService = new UserService();
        UserDAC userDAC = new UserDAC(dbUrl, userService);
        loginController = new LoginController(userService, userDAC);
        isLogin = Boolean.FALSE;

    }

    /**
     * Return true if username and password of given loginJSON matches with database
     * @param userJSON
     * @return true if username and password are valid
     */
    @PostMapping(value = "/api/login", consumes = "application/json", produces = "application/json")
    public StatusJSON verifyLogin(@RequestBody UserJSON userJSON) {
        loginController.read();
        isLogin = loginController.check(userJSON.getUsername(), userJSON.getPassword());
        return new StatusJSON(isLogin);

    }

    @PostMapping(value = "/api/signup", consumes = "application/json", produces = "application/json")
    public StatusJSON addUser(@RequestBody UserJSON userJSON){
        loginController.read();
        boolean res = loginController.addUser(userJSON.getUsername(), userJSON.getPassword());
        if (res){
            loginController.write();
        }
        return new StatusJSON(res);
    }

}