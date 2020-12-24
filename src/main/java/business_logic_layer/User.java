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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getUserID() {
    return userID;
  }

  public List<Integer> getFriendList() {
    return friendList;
  }

  public void addFriend(int friendID) {
    this.friendList.add(friendID);
  }

  public void removeFriend(int friendID) {
    this.friendList.remove(friendID);
  }

  public boolean isFriend(int friendID) {
    // 0 for not friend, 1 for is friend
    return (friendList.indexOf(friendID) != -1);
  }

}
