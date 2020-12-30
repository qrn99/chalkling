package teamchalkling.chalkling.jpa.user;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository user_repository;
    private User currentUser;

    @Inject
    public UserServiceImpl( UserRepository userRepository) {
        this.user_repository = userRepository;
    }

    /**
     * Sets the current user logged in
     * @param userName  String  the username of the user
     */
    @Override
    public void setCurrentUser(String userName){
        currentUser = getUserByUsername(userName);
    }

    /**
     * Gets the current user's ID, that is logged in
     * @return User the User that currently logged in
     */
    // TODO: Return user?
    @Override
    public User getCurrentUser(){
        if (currentUser != null){
            return currentUser;
        }
        return null;
    }

    /**
     * Gets all the users in the system
     * @return List<User>    all the users in the system
     */
    @Override
    public List<User> getAllUsers(){
        return user_repository.findAll();
    }


    /**
     * Create user account and add to list of user accounts
     * @param name String username of user account
     * @param password String password of user account
     */
    @Override
    public void addUser(String name, String password){
        User newUser = new User(name, password);
        user_repository.save(newUser);
    }

    /**
     * Remove the user if it exists
     * @param username String username of user account being removed
     * @return boolean true or false if user removed
     */
    @Override
    public boolean removeUser(String username) {
        if (!userExists(username)) return false;
        else {
            user_repository.delete(getUserByUsername(username));
            return true;
        }
    }

    /**
     * Checks if User with the given username exists
     * @param username the username of user account that is being checked
     * @return true or false whether User with the given username exists in list of users
     */
    @Override
    public boolean userExists(String username) {
        return !(getUserByUsername(username) == null);
    }

    /**
     * Verifies if username and password matches the credentials stored for that user account (if it exists)
     * @param username String username input for user account
     * @param password String password input for user account
     * @return True or False whether the username and the password matches an account in the list of user accounts
     */
    @Override
    public boolean canLogin(String username, String password) {
        if (!userExists(username)) return false;
        else return (password.equals(getUserByUsername(username).getPassword()));
    }

    /**
     * Add friend into login user's friend list
     * @param   username1  String  the username of the friend the user wants to add
     * @param   username2  String  target user to add to their friends' list
     * @return             boolean  True if add successfully, otherwise false
     */
    // TODO: Should it be username or userID?
    @Override
    public boolean addFriend(String username1, String username2){
        User user1 = getUserByUsername(username1);
        User user2 = getUserByUsername(username2);
        if (user1 == null || user2 == null) return false;
        else if (user1.isFriend(user2.getUserId())) return false; // already friend
        else {
            user1.addFriend(user2.getUserId());
            user_repository.save(user1);
            return true;
        }
    }

    /**
     * Remove friend from the login user's friend list
     * @param   username1  String  the username of the friend the user wants to remove
     * @param   username2  String  target user to remove from their friends' list
     * @return             boolean  True if remove successfully, otherwise false
     */
    // TODO: Should it be username or userID?
    @Override
    public boolean removeFriend(String username1, String username2){
        User user1 = getUserByUsername(username1);
        User user2 = getUserByUsername(username2);
        if (user1 == null || user2 == null) return false;
        else if (!user1.isFriend(user2.getUserId())) return false; // already not friend
        else {
            user1.removeFriend(user2.getUserId());
            user_repository.save(user1);
            return true;
        }
    }

    /*
    Helper function to find the user in the system
     */
    private User getUserByUsername(String username){
        Optional<User> temp = user_repository.findByUsername(username);
        return temp.orElse(null);
    }

}
