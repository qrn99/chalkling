package user_system;

import teamchalkling.chalkling.user_system.*;
import org.junit.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {teamchalkling.chalkling.config.PersistenceContext.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UserRepository.class))
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp(){
        this.userService = new UserServiceImpl(userRepository);
        userService.addUser("user1", "$2a$10$vJ7jWUxKCI7stLVtgtpszO", "$2a$10$vJ7jWUxKCI7stLVtgtpszOnq91hVZttt9TbQ401fjqdz9ct5iW0Ju");
        userService.addUser("user2", "$2a$10$Fxh.tTBv2V3MikJTIXB2.O", "$2a$10$Fxh.tTBv2V3MikJTIXB2.OlN324mu9cvl.ceKpXwAP/cTFU2Bf3UG");
        userService.addUser("user3", "$2a$10$5wS1RHT.rFVyhlNowhD/Fu", "$2a$10$5wS1RHT.rFVyhlNowhD/FuX6OHfvgW0lo6AQB4UEmodWPPmFC8qIC");
    }

    @Test
    public void testUserExists(){
        assertTrue(userService.userExists("user1"));
        assertTrue(userService.userExists("user2"));
        assertTrue(userService.userExists("user3"));
        assertFalse(userService.userExists("user4"));
    }

    @Test
    public void testUserRemove(){
        assertTrue(userService.removeUser("user3"));
        assertFalse(userService.removeUser("user3"));
    }

//    @Test
//    public void testAddFriend(){
//        assertTrue(userService.addFriend("user1", "user2"));
//
//        // TODO: One way or both-ways friendship?
//        //  May want to change to assert True or False depending on this question.
//        assertTrue(userService.addFriend("user2", "user1"));
//
//        assertFalse(userService.addFriend("user1", "user2"));
//        assertTrue(userService.addFriend("user1", "user3"));
//    }
//
//    @Test
//    public void testRemoveFriend(){
//        assertTrue(userService.addFriend("user1", "user2"));
//        assertTrue(userService.addFriend("user1", "user3"));
//
//        assertTrue(userService.removeFriend("user1", "user2"));
//
//        // TODO: One way or both-ways friendship?
//        //  May want to change to assert True or False depending on this question.
//        assertFalse(userService.removeFriend("user2", "user1"));
//
//        assertTrue(userService.removeFriend("user1", "user3"));
//    }
//
//    @Test
//    public void testDNEFriend(){
//        // TODO: add later
//    }

    @After
    public void tearDown(){
        userRepository.deleteAll();
    }
}
