package business_logic_layer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class LoginWebAPI extends HttpServlet {


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    String requestUrl = request.getRequestURI();
    String username = requestUrl.substring("/login/username".length());
    String password = requestUrl.substring("/login/password".length());

    LoginController loginController = new LoginController();
    Boolean bool = loginController.check(username, password);

    String json = "{\n";
    json += "\"username\": " + JSONObject.quote(username) + ",\n";
    json += "\"password\": " + JSONObject.quote(password) + ",\n";
    json += "\"success\": " + bool + "\n";
    json += "}";
    response.getOutputStream().println(json);

  }

}