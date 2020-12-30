package teamchalkling.chalkling.jpa.user;

import java.util.List;

public interface UserService {

    void setCurrentUser(String userName);

    // TODO: Return user? return null right now
    User getCurrentUser();

    List<User> getAllUsers();

    void addUser(String name, String password);

    boolean removeUser(String username);

    boolean userExists(String username);

    boolean canLogin(String username, String password);

    // TODO: Should it be username or userID?
    boolean addFriend(String username1, String username2);

    // TODO: Should it be username or userID?
    boolean removeFriend(String username1, String username2);

}
