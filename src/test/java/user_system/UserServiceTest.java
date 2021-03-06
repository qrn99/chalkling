package user_system;

import com.chalkling.config.PersistenceContext;
import com.chalkling.user_system.UserEntity;
import com.chalkling.user_system.UserRepository;
import com.chalkling.user_system.UserService;
import com.chalkling.user_system.UserServiceImpl;
import org.junit.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@DataJpaTest(properties = {"spring.test.database.replace=NONE", "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UserRepository.class))
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    private UserEntity user1;
    private UserEntity user2;
    private UserEntity user3;

    private MockHttpServletRequest request;

    @Before
    public void setUp(){
        this.userService = new UserServiceImpl(userRepository);
        user1 = new UserEntity("user1", "$2a$10$vJ7jWUxKCI7stLVtgtpszO", "$2a$10$vJ7jWUxKCI7stLVtgtpszOnq91hVZttt9TbQ401fjqdz9ct5iW0Ju");
        user2 = new UserEntity("user2", "$2a$10$Fxh.tTBv2V3MikJTIXB2.O", "$2a$10$Fxh.tTBv2V3MikJTIXB2.OlN324mu9cvl.ceKpXwAP/cTFU2Bf3UG");
        user3 = new UserEntity("user3", "$2a$10$5wS1RHT.rFVyhlNowhD/Fu", "$2a$10$5wS1RHT.rFVyhlNowhD/FuX6OHfvgW0lo6AQB4UEmodWPPmFC8qIC");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        request = new MockHttpServletRequest("POST", "/api/login");
        request.setContentType("application/json");
    }

    @Test
    public void testAddUser(){
        assertEquals(3, userService.getAllUsers().size());
        userService.addUser("Yining", "$2a$10$5wS1RHT.rFVyhlNowhD/Fu", "$2a$10$5wS1RHT.rFVyhlNowhD/FuX6OHfvgW0lo6AQB4UEmodWPPmFC8qIC");
        assertEquals(4, userService.getAllUsers().size());
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
    public void testGetUserByUserId(){
        assertNotNull(userService.getUserByUserId(user1.getUserId()));
        assertNotNull(userService.getUserByUserId(user2.getUserId()));
        assertNotNull(userService.getUserByUserId(user3.getUserId()));
    }

    @Test
    public void testGetUserIdByUsername(){
        assertEquals(user1.getUserId(), userService.getUserIdByUsername("user1"));
        assertEquals(user2.getUserId(), userService.getUserIdByUsername("user2"));
        assertEquals(user3.getUserId(), userService.getUserIdByUsername("user3"));
    }

    @Test
    public void testGetUsernameByUserId(){
        assertEquals("user1", userService.getUsernameByUserId(user1.getUserId()));
        assertEquals("user2", userService.getUsernameByUserId(user2.getUserId()));
        assertEquals("user3", userService.getUsernameByUserId(user3.getUserId()));
        assertEquals("", userService.getUsernameByUserId(-1));
    }

    @Test
    public void testAddFriend(){
        assertTrue(userService.addFriend("user1", "user2"));
        assertTrue(userService.addFriend("user1", "user3"));

        // duplicate adding
        assertFalse(userService.addFriend("user1", "user2"));
        // Two way friendship
        assertFalse(userService.addFriend("user2", "user1"));

        // get user1 friend list
        List<Integer> actual_friendList1 = userService.getFriendList("user1");
        List<Integer> expected_friendList1 = new ArrayList<>(Arrays.asList(user2.getUserId(), user3.getUserId()));
        assertEquals(actual_friendList1, expected_friendList1);

        // get user2 friend list
        List<Integer> actual_friendList2 = userService.getFriendList("user2");
        List<Integer> expected_friendList2 = new ArrayList<>(Arrays.asList(user1.getUserId()));
        assertEquals(actual_friendList2, expected_friendList2);

    }

    @Test
    public void testRemoveFriend(){
        assertTrue(userService.addFriend("user1", "user2"));
        assertTrue(userService.addFriend("user1", "user3"));

        assertTrue(userService.removeFriend("user1", "user2"));
        // duplicate removing, user2 get user1 friend removed automatically
        assertFalse(userService.removeFriend("user2", "user1"));

        // get user1 friend list
        List<Integer> actual_friendList1 = userService.getFriendList("user1");
        List<Integer> expected_friendList1 = new ArrayList<>(Arrays.asList(user3.getUserId()));
        assertEquals(actual_friendList1, expected_friendList1);

        // get user2 friend list
        List<Integer> actual_friendList2 = userService.getFriendList("user2");
        assertEquals(new ArrayList<>(), actual_friendList2);

        assertTrue(userService.removeFriend("user1", "user3"));
        // user3 get user1 friend removed automatically
        assertFalse(userService.removeFriend("user3", "user1"));
    }

    @Test
    public void testGetFriendList(){
        assertTrue(userService.addFriend("user1", "user2"));
        assertTrue(userService.addFriend("user1", "user3"));
        List<Integer> actual_friendList = userService.getFriendList("user1");
        List<Integer> expected_friendList = new ArrayList<>();
        expected_friendList.add(user2.getUserId());
        expected_friendList.add(user3.getUserId());
        assertTrue(actual_friendList.containsAll(expected_friendList));
        assertTrue(expected_friendList.containsAll(actual_friendList));
    }


    @Test
    public void testSetCurrentUser_and_GetCurrentUser(){
        assertNull(userService.getCurrentUser(request));
        userService.setCurrentUser(request, "user1");
        assertEquals("user1", userService.getCurrentUser(request));
    }
}
