package com.chalkling.friend_system;

import java.util.List;

public class GetFriendsJSON {
    private List<String> friendList;
    public GetFriendsJSON(List<String> friendList){
        this.friendList = friendList;
    }
    public List<String> getFriendList() {
        return friendList;
    }
    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }
}
