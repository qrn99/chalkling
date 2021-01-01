import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import teamchalkling.chalkling.business_logic_layer.LoginController;
import teamchalkling.chalkling.business_logic_layer.LoginWebAPI;
import teamchalkling.chalkling.business_logic_layer.UserJSON;
import teamchalkling.chalkling.jpa.user.UserRepository;
import teamchalkling.chalkling.jpa.user.UserService;
import teamchalkling.chalkling.jpa.user.UserServiceImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {teamchalkling.chalkling.jpa.config.PersistenceContext.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UserRepository.class))
public class LoginWebAPITest {
    private LoginWebAPI loginWebAPI;
    private final UserJSON user0 = new UserJSON("test0", "password123");
    private final UserJSON user0_sim = new UserJSON("test0", "password1234");
    private final UserJSON user1 = new UserJSON("test1", "?#$%:3;");
    private final UserJSON user2 = new UserJSON("test2", "helloworld");

    private LoginController loginController;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp(){
        UserService userService = new UserServiceImpl(userRepository);
        loginController = new LoginController(userService);
        loginWebAPI = new LoginWebAPI(loginController);
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
