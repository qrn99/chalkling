import org.junit.Before;
import org.junit.Test;
import teamchalkling.chalkling.business_logic_layer.LoginWebAPI;
import teamchalkling.chalkling.business_logic_layer.UserJSON;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginWebAPITest {
    private LoginWebAPI loginWebAPI;
    private final UserJSON user0 = new UserJSON("test0", "password123");
    private final UserJSON user0_sim = new UserJSON("test0", "password1234");
    private final UserJSON user1 = new UserJSON("test1", "?#$%:3;");
    private final UserJSON user2 = new UserJSON("test2", "helloworld");

    @Before
    public void setUp(){
        loginWebAPI = new LoginWebAPI();
    }

    @Test
    public void testSignUp(){
        assertTrue(loginWebAPI.addUser(user0).getStatus());
        assertFalse(loginWebAPI.addUser(user0_sim).getStatus()); // preserve uniqueness
    }

    @Test
    public void testLogIn(){
        assertTrue(loginWebAPI.addUser(user0).getStatus());
        assertTrue(loginWebAPI.addUser(user1).getStatus());
        assertTrue(loginWebAPI.verifyLogin(user0).getStatus());
        assertTrue(loginWebAPI.verifyLogin(user1).getStatus());

        assertFalse(loginWebAPI.verifyLogin(user2).getStatus());
        assertFalse(loginWebAPI.verifyLogin(user0_sim).getStatus());
    }
}
