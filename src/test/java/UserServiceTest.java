import teamchalkling.chalkling.business_logic_layer.UserService;
import teamchalkling.chalkling.database_layer.UserDAC;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class UserServiceTest {

    private UserService userService;

    @Before
    public void setUp(){
        userService = new UserService();
        userService.addUser("user1", "pass");
        userService.addUser("user2", "pass");
        userService.addUser("user3", "pass");
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

        // TODO: Fix the issue
        // assertFalse(userService.removeFriend("user1", "user3"));
    }

    @Test
    public void testDNEFriend(){
        // Should have a test case for friends that become DNE.
    }

}
