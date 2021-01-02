package teamchalkling.chalkling.user_system;

import java.util.List;

public interface UserService {

    void setCurrentUser(String userName);

    // TODO: Return user? return null right now
    UserEntity getCurrentUser();

    List<UserEntity> getAllUsers();

    void addUser(String name, String salt, String hash);

    boolean removeUser(String username);

    boolean userExists(String username);

    /**
     * Return salt if username exist
     * @param username the username of user account
     * @return String salt
     */
    String getUserSalt(String username);

    boolean canLogin(String username, String salt, String hash);

    int getUserIdByUserName(String username);

    UserEntity getUserByUserId(int userId);

//    // TODO: Should it be username or userID?
//    boolean addFriend(String username1, String username2);
//
//    // TODO: Should it be username or userID?
//    boolean removeFriend(String username1, String username2);

}
