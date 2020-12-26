package database_layer;

import Main.Application;
import business_logic_layer.User;
import business_logic_layer.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest

public class UserRepositoryTest {
    @Autowired
    private UserRepository testUserRepository;
//    @Autowired
//    private UserService testUserService;
//    @Autowired
//    private UserDAC testUserDAC;
//    private String url = "jdbc:postgres://hutwxskavlscry:e195b09d98154c2bd35034acc320aec96749b2a16d51240747c4666650f75cab@ec2-52-44-235-121.compute-1.amazonaws.com:5432/d812ersdhkuu5n";

//    @Before
//    public void setup(){
//        testUserService = new UserService();
//        testUserDAC = new UserDAC(url, testUserService, testUserRepository);
//        testUserService.addUser("user1", "pass");
//        testUserService.addUser("user2", "pass");
//        testUserService.addUser("user3", "pass");
//    }

    @Test
    public void testSave(){
//        testUserDAC.saveUsers();
        testUserRepository.save(new User("user1", "pass"));
        List<User> users = (List<User>) testUserRepository.findAll();
        assertThat(users.size(), is(1));
    }

//    @Test
//    public void testWrite(){
//        testUserDAC.write();
//    }
}
