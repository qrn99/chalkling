package business_logic_layer;

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
     * @param friendUsername  String  the username of the friend the user wants to add
     * @return  boolean  True if add successfully, otherwise false
     */
    public boolean addFriend(String friendUsername){
        User friend = getUserByUsername(friendUsername);
        if (friend == null) return false;
        else if (currentUser == null) return false;
        else if (currentUser.isFriend(friend.getUserID())) return false; // already friend
        else {
            currentUser.addFriend(friend.getUserID());
            return true;
        }
    }

    /**
     * Remove friend from the login user's friend list
     * @param friendUsername  String  the username of the friend the user wants to remove
     * @return  boolean  True if remove successfully, otherwise false
     */
    public boolean removeFriend(String friendUsername){
        User friend = getUserByUsername(friendUsername);
        if (friend == null) return false;
        else if (currentUser == null) return false;
        else if (!currentUser.isFriend(friend.getUserID())) return false; // not friend yet
        else {
            currentUser.removeFriend(friend.getUserID());
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
