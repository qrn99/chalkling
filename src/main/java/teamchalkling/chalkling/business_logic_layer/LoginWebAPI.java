package teamchalkling.chalkling.business_logic_layer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.json.JSONObject;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.atomic.AtomicLong;


@RestController

public class LoginWebAPI {

    private Boolean isLogin;


    public LoginWebAPI(){
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
    @GetMapping(value = "/get")
    public ResponseEntity getBool() {
        return ResponseEntity.ok(isLogin);
    }

    /**
     * Check if username and password matches
     * @param username username of current user
     * @param password password of current user
     * @return true if username and password matches
     */
    @GetMapping(value = "/post")
    public ResponseEntity addToList(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        isLogin = new Boolean(username.equals(password));
        return ResponseEntity.ok(isLogin);
    }

}