import org.junit.Before;
import org.junit.Test;
import teamchalkling.chalkling.business_logic_layer.LoginController;
import teamchalkling.chalkling.business_logic_layer.UserService;
import teamchalkling.chalkling.database_layer.UserDAC;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LoginControllerTest {

    private LoginController loginController;

    @Before
    public void setUp(){
        UserService userService = new UserService();
        UserDAC userDAC = new UserDAC("", userService);
        loginController = new LoginController(userService, userDAC);

        loginController.addUser("user1", "pass1");
        loginController.addUser("user2", "pass2");

    }

    @Test
    public void testCheck(){
        assertTrue(loginController.check("user1", "pass1"));  // correct password
        assertTrue(loginController.check("user2", "pass2"));
        assertFalse(loginController.check("user1", "pass2")); // wrong password
        assertFalse(loginController.check("user3", "pass3")); // invalid username

    }

}
