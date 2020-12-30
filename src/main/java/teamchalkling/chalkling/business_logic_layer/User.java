package teamchalkling.chalkling.business_logic_layer;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userID;
    private String username;
    private String salt;
    private String hash;
    private List<Integer> friendList;

    private static int id_counter = 0;

    public User(String username, String salt, String hash) {
        this.username = username;
        this.salt = salt;
        this.hash = hash;
        this.userID = id_counter;
        this.friendList = new ArrayList<>();
        id_counter ++;
    }

    /**
     * Get username of current user
     * @return the username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username of current user
     * @param username username of current user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get salt of current user
     * @return the salt of user
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Set salt of current user
     * @param salt salt of current user
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Get hash of current user
     * @return the hash of user
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set hash of current user
     * @param hash hash of current user
     */
    public void setHash(String hash) {
        this.hash = hash;
    }


    /**
     * Get userID of current user
     * @return int unique userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Get frendList of current user
     * @return friend list as list of integer
     */
    public List<Integer> getFriendList() {
        return friendList;
    }

    /**
     * Add friend to friendList of current user
     */
    public void addFriend(int friendID) {
        this.friendList.add(friendID);
    }

    /**
     * Remove friend to friendList of current user
     */
    public void removeFriend(int friendID) {
        if (this.isFriend(friendID)){
            this.friendList.remove((Integer) friendID);
        }
    }

    /**
     * Check if given user is a friend of current user
     * @param friendID ID of another user
     * @return true if given user is a friend of current user
     */
    public boolean isFriend(int friendID) {
        return friendList.contains(friendID);
    }

}
