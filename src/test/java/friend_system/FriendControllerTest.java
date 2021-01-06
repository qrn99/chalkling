package friend_system;

import com.chalkling.config.PersistenceContext;
import com.chalkling.friend_system.*;
import com.chalkling.user_system.*;
import org.junit.Before;
import org.junit.Test;
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
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@DataJpaTest(properties = {"spring.test.database.replace=NONE", "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UserRepository.class))

public class FriendControllerTest {
    private FriendController friendController;
    private UserService userService;
    @Autowired private UserRepository userRepository;

    private UserEntity user1;
    private UserEntity user2;
    private UserEntity user3;

    private MockHttpServletRequest request;

    @Before
    public void setUp(){
        user1 = new UserEntity("user1", "$2a$10$vJ7jWUxKCI7stLVtgtpszO", "$2a$10$vJ7jWUxKCI7stLVtgtpszOnq91hVZttt9TbQ401fjqdz9ct5iW0Ju");
        user2 = new UserEntity("user2", "$2a$10$Fxh.tTBv2V3MikJTIXB2.O", "$2a$10$Fxh.tTBv2V3MikJTIXB2.OlN324mu9cvl.ceKpXwAP/cTFU2Bf3UG");
        user3 = new UserEntity("user3", "$2a$10$5wS1RHT.rFVyhlNowhD/Fu", "$2a$10$5wS1RHT.rFVyhlNowhD/FuX6OHfvgW0lo6AQB4UEmodWPPmFC8qIC");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        userService = new UserServiceImpl(userRepository);
        friendController = new FriendController(userService);

        request = new MockHttpServletRequest("POST", "/api/login");
        request.setContentType("application/json");
    }

    @Test
    public void testGetFriends(){
        // let user1 login
        userService.setCurrentUser(request, user1.getUsername());
        // set up friends
        userService.addFriend("user1", "user2");
        userService.addFriend("user1", "user3");

        List<String> friendList = new ArrayList<>(Arrays.asList("user2", "user3"));

        assertEquals(friendList, friendController.getFriends(request).getFriendList());
    }

    @Test
    public void testGetFriendsImproperLogin(){
        // no one is login with request
        // set up friends
        userService.addFriend("user1", "user2");
        userService.addFriend("user1", "user3");

        assertEquals(Arrays.asList("username is null", "you aren't login", "not bc you dont have friends"), friendController.getFriends(request).getFriendList());
    }

    @Test
    public void testAddFriend(){
        userService.setCurrentUser(request, user1.getUsername());
        ChangeFriendJSON changeFriendJSON = new ChangeFriendJSON("user2");
        friendController.addFriend(changeFriendJSON, request);

        List<String> friendList1 = new ArrayList<>(Collections.singletonList("user2"));
        List<Integer> friendList_id = userService.getFriendList(user1.getUsername());
        List<String> friendList = new ArrayList<>();

        for (Integer num: friendList_id){
            friendList.add(userService.getUsernameByUserId(num));
        }

        assertEquals(friendList1, friendList);
    }

    @Test
    public void testRemoveFriend(){
        userService.setCurrentUser(request, user1.getUsername());
        ChangeFriendJSON changeFriendJSON = new ChangeFriendJSON("user2");
        friendController.addFriend(changeFriendJSON, request);
        friendController.removeFriend(changeFriendJSON, request);

        List<Integer> friendList_id = userService.getFriendList(user1.getUsername());

        assertEquals(new ArrayList<>(), friendList_id);
    }

}
