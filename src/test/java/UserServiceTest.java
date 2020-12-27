import teamchalkling.chalkling.business_logic_layer.UserService;
import teamchalkling.chalkling.database_layer.UserDAC;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class UserServiceTest {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private UserDAC uDAC;

    private UserService userService;

    @Bean
    public UserDAC uDAC() {
        return new UserDAC(dbUrl, userService);
    }

    @Before
    public void setUp(){
        userService = new UserService();
        uDAC = new UserDAC(dbUrl, userService);
        userService.addUser("user1", "pass");
        userService.addUser("user2", "pass");
        userService.addUser("user3", "pass");
    }

    @Test
    public void testAdd(){
        assertTrue(userService.userExists("user1"));
        assertTrue(userService.userExists("user2"));
        assertTrue(userService.userExists("user3"));
        assertFalse(userService.userExists("user4"));
    }

}
