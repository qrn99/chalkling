package com.chalkling.friend_system;

import java.util.List;

public class GetFriendsJSON {
    private List<String> friendsList;
    public GetFriendsJSON(List<String> friendsList){
        this.friendsList = friendsList;
    }
    public List<String> getFriendsList() {
        return friendsList;
    }
    public void setFriendsList(List<String> friendsList) {
        this.friendsList = friendsList;
    }
}
