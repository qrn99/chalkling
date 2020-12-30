package teamchalkling.chalkling.business_logic_layer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.json.JSONObject;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamchalkling.chalkling.database_layer.UserDAC;

import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.atomic.AtomicLong;


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

    @GetMapping(value = "/api/signup", consumes = "application/json")
    public StatusJSON addUser(@RequestBody UserJSON userJSON){
        loginController.addUser(userJSON.getUsername(), userJSON.getPassword());
        loginController.write();
        return new StatusJSON(true);
    }

}