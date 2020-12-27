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

    private List<Boolean> boolList = new ArrayList();


    public LoginWebAPI(){
        boolList.add(new Boolean(true));

    }

    @GetMapping(value = "/")
    public ResponseEntity index() {
        return ResponseEntity.ok(boolList);
    }


    @GetMapping(value = "/get")
    public ResponseEntity getBool(@RequestParam(value="id") Long id) {
        Boolean itemToReturn = this.boolList.get(id.intValue());

        return ResponseEntity.ok(itemToReturn);
    }

    @GetMapping(value = "/getlatest")
    public ResponseEntity getBool() {
        Boolean itemToReturn = this.boolList.get(this.boolList.size()-1);
        return ResponseEntity.ok(itemToReturn);
    }

    @GetMapping(value = "/post")
    public ResponseEntity addToList(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        boolList.add(new Boolean(username.equals(password)));
        return ResponseEntity.ok(boolList);
    }

}