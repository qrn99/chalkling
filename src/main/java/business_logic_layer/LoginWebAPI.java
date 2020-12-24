package business_logic_layer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginWebAPI extends HttpServlet {


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    return;
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    String requestUrl = request.getRequestURI();
    String username = requestUrl.substring("/login/username".length());
    String password = requestUrl.substring("/login/password".length());

    LoginController loginController = new LoginController();
    Boolean bool = loginController.check(username, password);

    if (bool){
      // login credentials are correct

    }
    else{

    }
  }



}