package com.chalkling.user_system;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository user_repository;
    private UserEntity currentUserEntity;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.user_repository = userRepository;
    }

    /**
     * Gets all the users in the system
     * @return List<UserEntity>    all the users in the system
     */
    @Override
    public List<UserEntity> getAllUsers(){
        return user_repository.findAll();
    }

    /**
     * Create user account and add to list of user accounts
     * @param name String username of user account
     * @param salt String salt of user account password
     * @param hash String hash of user account password
     */
    @Override
    public void addUser(String name, String salt, String hash){
        UserEntity newUserEntity = new UserEntity(name, salt, hash);
        user_repository.save(newUserEntity);
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
     * Checks if UserEntity with the given username exists
     * @param username the username of user account that is being checked
     * @return true or false whether UserEntity with the given username exists in list of users
     */
    @Override
    public boolean userExists(String username) {
        return !(getUserByUsername(username) == null);
    }

    /**
     * Return salt if username exist
     * @param username the username of user account
     * @return String salt
     */
    @Override
    public String getUserSalt(String username) {
        if (userExists(username)) {
            return getUserByUsername(username).getSalt();
        }
        return null;
    }

    /**
     * Verifies if username and password matches the credentials stored for that user account (if it exists)
     * @param username String username input for user account
     * @param salt String salt input for user account
     * @param hash String hash input for user account
     * @return True or False whether the username and the password matches an account in the list of user accounts
     */
    @Override
    public boolean canLogin(String username, String salt, String hash) {
        if (!userExists(username)) return false;
        else {
            UserEntity user = getUserByUsername(username);
            return (salt.equals(user.getSalt()) && hash.equals(user.getHash()));
        }
    }

    @Override
    public int getUserIdByUsername(String username) {
        if (!userExists(username)) return -1; // user DNE
        else return getUserByUsername(username).getUserId();
    }

    @Override
    public String getUsernameByUserId(int userId) {
        UserEntity user = getUserByUserId(userId);
        if (getUserByUserId(userId) == null) return "";
        else return user.getUsername();
    }

    @Override
    public UserEntity getUserByUserId(int userId) {
        Optional<UserEntity> temp = user_repository.findById(userId);
        return temp.orElse(null);
    }

    // TODO: Properly implement
    // TODO: Use JWT for logged in sessions.
    @Override
    public void setCurrentUser(HttpServletRequest request, String username){
        request.getSession().setAttribute("CHALKLING_USERNAME", username);
    }

    /**
     * Returns username.
     * @param request The client's browser request.
     * @return username if valid session. Null otherwise.
     */
    // TODO: Use JWT?
    @Override
    public String getCurrentUser(HttpServletRequest request){
        if (request.getSession(false) != null) {
            return (String) request.getSession(false).getAttribute("CHALKLING_USERNAME");
        }
        return null;
    }

    /**
     * Return the friendList for a user
     * @param username  String          the username of the user
     * @return          List<Integer>   the list of friends id
     */
    @Override
    public List<Integer> getFriendList(String username){
        UserEntity user = getUserByUsername(username);
        if (user == null) return new ArrayList<>();
        else return user.getFriendList();
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
        UserEntity userEntity1 = getUserByUsername(username1);
        UserEntity userEntity2 = getUserByUsername(username2);
        if (userEntity1 == null || userEntity2 == null) return false;
        // friends already
        else if (this.isFriend(userEntity1, userEntity2.getUserId())
                && this.isFriend(userEntity2, userEntity1.getUserId())) return false;
        else {
            this.addFriendToUser(userEntity1, userEntity2.getUserId());
            this.addFriendToUser(userEntity2, userEntity1.getUserId());
            user_repository.save(userEntity1);
            user_repository.save(userEntity2);
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
        UserEntity userEntity1 = getUserByUsername(username1);
        UserEntity userEntity2 = getUserByUsername(username2);
        if (userEntity1 == null || userEntity2 == null) return false;
        // not friends already
        else if (!this.isFriend(userEntity1, userEntity2.getUserId())
                && !this.isFriend(userEntity2, userEntity1.getUserId())) return false;
        else {
            this.removeFriendToUser(userEntity1, userEntity2.getUserId());
            this.removeFriendToUser(userEntity2, userEntity1.getUserId());
            user_repository.save(userEntity1);
            user_repository.save(userEntity2);
            return true;
        }
    }


    // Helper functions
    /*
     * Get the user in the system by username
     * @param username  String  the username of the user
     */
    private UserEntity getUserByUsername(String username){
        Optional<UserEntity> temp = user_repository.findByUsername(username);
        return temp.orElse(null);
    }

    /*
     * Add friend to the friendList of the user
     * @param user     UserEntity   the user
     * @param friendID int          the userId of the friend user
     */
    private void addFriendToUser(UserEntity user, int friendID) {
        if (!isFriend(user, friendID)){
            user.getFriendList().add(friendID);
        }
    }

    /*
     * Remove friend from the friendList of the user
     * @param user     UserEntity   the user
     * @param friendID int          the userId of the friend user
     */
    private void removeFriendToUser(UserEntity user, int friendID) {
        if (isFriend(user, friendID)){
            user.getFriendList().remove((Integer) friendID);
        }
    }

    /*
     * Check if given user is a friend of the user
     * @param user     UserEntity   the user
     * @param friendID int          ID of another user
     * @return true if given friendId is already a friend of the user
     */
    private boolean isFriend(UserEntity user, int friendID) {
        return user.getFriendList().contains(friendID);
    }

}
