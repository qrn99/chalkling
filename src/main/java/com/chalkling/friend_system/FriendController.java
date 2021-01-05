package com.chalkling.friend_system;

import com.chalkling.message_system.AddMessageJSON;
import com.chalkling.user_system.UserService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {

    private final UserService userService;

    @Inject
    public FriendController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/api/friends", produces = "application/json")
    public GetFriendsJSON getFriends(HttpServletRequest request){
        String username = userService.getCurrentUser(request);

        List<Integer> friendList_id = userService.getFriendList(username);
        List<String> friendList = new ArrayList<>();
        for (Integer num: friendList_id){
            friendList.add(userService.getUsernameByUserId(num));
        }

        return new GetFriendsJSON(friendList);
    }


    @PatchMapping(value = "/api/friends", consumes = "application/json")
    public void addFriend(@RequestBody ChangeFriendJSON changeFriendJSON, HttpServletRequest request) {
        String username = userService.getCurrentUser(request);
        String friendName = changeFriendJSON.getFriend();
        userService.addFriend(username, friendName);

    }


    @PatchMapping(value = "/api/friends", consumes = "application/json", produces = "application/json")
    public void removeFriend(@RequestBody ChangeFriendJSON changeFriendJSON, HttpServletRequest request) {
        String username = userService.getCurrentUser(request);
        String friendName = changeFriendJSON.getFriend();
        userService.removeFriend(username, friendName);
    }
}
