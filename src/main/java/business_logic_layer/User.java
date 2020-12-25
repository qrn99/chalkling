package business_logic_layer;

import java.util.ArrayList;
import java.util.List;

public class User {
  private String username;
  private int userID;
  private String password;
  private List<Integer> friendList;

  private static int id_counter = 0;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.userID = id_counter;
    this.friendList = new ArrayList<Integer>();
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
   * Get password of current user
   * @return the password of user
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set password of current user
   * @param password password of current user
   */
  public void setPassword(String password) {
    this.password = password;
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
    this.friendList.remove(friendID);
  }

  /**
   * Check if given user is a friend of current user
   * @param friendID ID of another user
   * @return true if given user is a friend of current user
   */
  public boolean isFriend(int friendID) {
    // 0 for not friend, 1 for is friend
    return (friendList.indexOf(friendID) != -1);
  }

}
