package login_system;

import com.chalkling.config.PersistenceContext;
import com.chalkling.user_system.UserRepository;
import com.chalkling.user_system.UserService;
import com.chalkling.user_system.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.chalkling.login_system.LoginController;
import com.chalkling.login_system.UserJSON;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
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
    }

    @Test
    public void testSignUp(){
        assertEquals("SUCCESS", loginController.addUser(user0).getStatus());
        assertEquals("USER_EXIST", loginController.addUser(user0_sim).getStatus()); // preserve uniqueness
    }

    @Test
    public void testLogIn(){
        assertEquals("SUCCESS", loginController.addUser(user0).getStatus());
        assertEquals("SUCCESS", loginController.addUser(user1).getStatus());
        // TODO: Fix testcases so that it uses mock HttpServletRequest.
//        assertTrue(loginController.verifyLogin(user0, null).getStatus());
//        assertTrue(loginController.verifyLogin(user1, null).getStatus());
//
//        assertFalse(loginController.verifyLogin(user2, null).getStatus());
//        assertFalse(loginController.verifyLogin(user0_sim, null).getStatus());
    }

}
