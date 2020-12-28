package teamchalkling.chalkling.business_logic_layer;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> userList = new ArrayList<>();
    private User currentUser = null;

    /**
     * Sets the current user logged in
     * @param userName  String  the username of the user
     */
    public void setCurrentUser(String userName){
        currentUser = getUserByUsername(userName);
    }

    /**
     * Gets the current user's ID, that is logged in
     * @return User the User that currently logged in
     */
    // TODO: Return user?
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
    public List<User> getAllUsers(){
        return userList;
    }


    /**
     * Create user account and add to list of user accounts
     * @param name String username of user account
     * @param password String password of user account
     */
    public void addUser(String name, String password){
        User newUser = new User(name, password);
        userList.add(newUser);
    }

    /**
     * Remove the user if it exists
     * @param username String username of user account being removed
     * @return boolean true or false if user removed
     */
    public boolean removeUser(String username) {
        User u = getUserByUsername(username);
        if (u != null){
            userList.remove(getUserByUsername(username));
            return true;
        }
        return false;
    }

    /**
     * Checks if User with the given username exists
     * @param username the username of user account that is being checked
     * @return true or false whether User with the given username exists in list of users
     */
    public boolean userExists(String username) {
        return !(getUserByUsername(username) == null);
    }

    /**
     * Verifies if username and password matches the credentials stored for that user account (if it exists)
     * @param username String username input for user account
     * @param password String password input for user account
     * @return True or False whether the username and the password matches an account in the list of user accounts
     */
    public boolean canLogin(String username, String password) {
        for (User user : userList) {
            if ((username.equals(user.getUsername())) && (password.equals(user.getPassword()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add friend into login user's friend list
     * @param   username1  String  the username of the friend the user wants to add
     * @param   username2  String  target user to add to their friends' list
     * @return             boolean  True if add successfully, otherwise false
     */
    // TODO: Should it be username or userID?
    public boolean addFriend(String username1, String username2){
        User user1 = getUserByUsername(username1);
        User user2 = getUserByUsername(username2);
        if (user1 == null || user2 == null) return false;
        else if (user1.isFriend(user2.getUserID())) return false; // already friend
        else {
            user1.addFriend(user2.getUserID());
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
    public boolean removeFriend(String username1, String username2){
        User user1 = getUserByUsername(username1);
        User user2 = getUserByUsername(username2);
        if (user1 == null || user2 == null) return false;
        else if (!user1.isFriend(user2.getUserID())) return false; // already not friend
        else {
            user1.removeFriend(user2.getUserID());
            return true;
        }
    }

    /*
    Helper function to find the user in the system
     */
    private User getUserByUsername(String username){
        for (User user: userList){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }
}
