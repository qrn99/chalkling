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
    private LoginController loginController;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    public LoginWebAPI(){
        UserService userService = new UserService();
        UserDAC userDAC = new UserDAC(dbUrl, userService);
        LoginController loginController = new LoginController(userDAC);
        this.loginController = loginController;
        isLogin = new Boolean(false);

    }


    /**
     * Return true if username and password of given loginJSON matches with database
     * @param loginJSON
     * @return true if username and password are valid
     */
    @PostMapping(value = "/api/login", consumes = "application/json", produces = "application/json")
    public LoginJSON addToList(@RequestBody LoginJSON loginJSON) {
        isLogin = loginController.check(loginJSON.getUsername(), loginJSON.getPassword());
        LoginJSON result = new LoginJSON(loginJSON.getUsername(), loginJSON.getPassword(), isLogin);
        return result;

    }

}