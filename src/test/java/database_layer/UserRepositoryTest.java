//package database_layer;
//
//import Main.Application;
//import business_logic_layer.User;
//import business_logic_layer.UserService;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.*;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.CoreMatchers.*;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes=Application.class)
////@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
//
//public class UserRepositoryTest {
//    @Autowired
//    private UserRepository testUserRepository;
////    @Autowired
////    private UserService testUserService;
////    @Autowired
////    private UserDAC testUserDAC;
//
////    @Before
////    public void setup(){
////        testUserService = new UserService();
////        testUserDAC = new UserDAC(url, testUserService, testUserRepository);
////        testUserService.addUser("user1", "pass");
////        testUserService.addUser("user2", "pass");
////        testUserService.addUser("user3", "pass");
////    }
//
//    @Test
//    public void testSave(){
////        testUserDAC.saveUsers();
//        testUserRepository.save(new User("user1", "pass"));
//        List<User> users = (List<User>) testUserRepository.findAll();
//        assertThat(users.size(), is(1));
//    }
//
////    @Test
////    public void testWrite(){
////        testUserDAC.write();
////    }
//}
