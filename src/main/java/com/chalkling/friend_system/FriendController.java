package com.chalkling.friend_system;

import com.chalkling.user_system.UserEntity;
import com.chalkling.user_system.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

        // TODO: Use username string to get the user's friends.
        //  This currently gets all users.
        List<String> userList = new ArrayList<>();
        for (UserEntity user: userService.getAllUsers()){
            userList.add(user.getUsername());
        }

        return new GetFriendsJSON(userList);
    }
}
