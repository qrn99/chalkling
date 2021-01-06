package com.chalkling.friend_system;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangeFriendJSON {
    private String friend;

    @JsonCreator
    public ChangeFriendJSON(@JsonProperty("friend") String friend) {
        this.friend = friend;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }
}
