import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import teamchalkling.chalkling.business_logic_layer.LoginController;
import teamchalkling.chalkling.jpa.user.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {teamchalkling.chalkling.jpa.config.PersistenceContext.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UserRepository.class))
public class LoginControllerTest {

    private LoginController loginController;
    @Autowired
    private UserRepository userRepository;

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

}
