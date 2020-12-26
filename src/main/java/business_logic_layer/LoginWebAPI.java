package business_logic_layer;

//import database_layer.UserDAC;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginWebAPI {
//  private final UserDAC userDAC;

//  public LoginWebAPI(UserDAC userDAC){
//    this.userDAC = userDAC;
//  }

  @RequestMapping("/")
  String index() {
    return "index";
  }

  @RequestMapping("/login")
  String login(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
    UserService userService = new UserService();
    LoginController loginController = new LoginController(userService);
    loginController.check(username, password);
    boolean bool = loginController.getLoginState();

    String json = "{\n";
    json += "\"username\": " + JSONObject.quote(username) + ",\n";
    json += "\"password\": " + JSONObject.quote(password) + ",\n";
    json += "\"isLogin\": " + bool + "\n";
    json += "}";

    return json;
  }

//  @RequestMapping("/db")
//  String db(Map<String, Object> model) {
//    try (Connection connection = databaseLogic.getDatabase().getConnection()) {
//      Statement stmt = connection.createStatement();
//      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//
//      ArrayList<String> output = new ArrayList<String>();
//      while (rs.next()) {
//        output.add("Read from DB: " + rs.getTimestamp("tick"));
//      }
//
//      model.put("records", output);
//      return "db";
//    } catch (Exception e) {
//      model.put("message", e.getMessage());
//      return "error";
//    }
//  }
}
