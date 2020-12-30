package jpa;

import teamchalkling.chalkling.jpa.user.*;
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
@ContextConfiguration(classes = {teamchalkling.chalkling.jpa.config.PersistenceContext.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UserRepository.class))
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp(){
        userRepository.save(new User("user1", "pass1"));
        userRepository.save(new User("user2", "pass2"));
        userRepository.save(new User("user3", "pass3"));
        this.userService = new UserServiceImpl(userRepository);
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

    @Test
    public void testAddFriend(){
        assertTrue(userService.addFriend("user1", "user2"));

        // TODO: One way or both-ways friendship?
        //  May want to change to assert True or False depending on this question.
        assertTrue(userService.addFriend("user2", "user1"));

        assertFalse(userService.addFriend("user1", "user2"));
        assertTrue(userService.addFriend("user1", "user3"));
    }

    @Test
    public void testRemoveFriend(){
        assertTrue(userService.addFriend("user1", "user2"));
        assertTrue(userService.addFriend("user1", "user3"));

        assertTrue(userService.removeFriend("user1", "user2"));

        // TODO: One way or both-ways friendship?
        //  May want to change to assert True or False depending on this question.
        assertFalse(userService.removeFriend("user2", "user1"));

        assertTrue(userService.removeFriend("user1", "user3"));
    }

    @Test
    public void testDNEFriend(){
        // Should have a test case for friends that become DNE.
    }

    @After
    public void tearDown(){
        userRepository.deleteAll();
    }
}
