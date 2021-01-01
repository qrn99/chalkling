package login_system;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import teamchalkling.chalkling.login_system.LoginController;
import teamchalkling.chalkling.login_system.UserJSON;
import teamchalkling.chalkling.user_system.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {teamchalkling.chalkling.config.PersistenceContext.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UserRepository.class))
public class LoginControllerTest {

    private LoginController loginController;
    @Autowired
    private UserRepository userRepository;

    // WebAPI stuff
    private final UserJSON user0 = new UserJSON("test0", "password123");
    private final UserJSON user0_sim = new UserJSON("test0", "password1234");
    private final UserJSON user1 = new UserJSON("test1", "?#$%:3;");
    private final UserJSON user2 = new UserJSON("test2", "helloworld");

    @Before
    public void setUp(){
        UserService userService = new UserServiceImpl(userRepository);
        loginController = new LoginController(userService);
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

    @Test
    public void testSignUp(){
        assertTrue(loginController.addUser(user0).getStatus());
        assertFalse(loginController.addUser(user0_sim).getStatus()); // preserve uniqueness
    }

    @Test
    public void testLogIn(){
        assertTrue(loginController.addUser(user0).getStatus());
        assertTrue(loginController.addUser(user1).getStatus());
        assertTrue(loginController.verifyLogin(user0).getStatus());
        assertTrue(loginController.verifyLogin(user1).getStatus());

        assertFalse(loginController.verifyLogin(user2).getStatus());
        assertFalse(loginController.verifyLogin(user0_sim).getStatus());
    }

}
