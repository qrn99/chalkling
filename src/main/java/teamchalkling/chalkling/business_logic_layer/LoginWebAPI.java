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
     * GET method: return current login state (false by default)
     * @return Boolean isLogin
     */
    @GetMapping(value = "/")
    public ResponseEntity index() {
        return ResponseEntity.ok(isLogin);
    }

    /**
     * GET method: return current login state
     * @return Boolean isLogin
     */
    @GetMapping(value = "/api/login/get")
    public ResponseEntity getBool() {
        return ResponseEntity.ok(isLogin);
    }

    /**
     * Check if username and password matches
     * @param username username of current user
     * @param password password of current user
     * @return true if username and password matches
     */
    @GetMapping(value = "/api/login")
    public ResponseEntity addToList(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        isLogin = loginController.check(username, password);
        return ResponseEntity.ok(isLogin);
    }

}