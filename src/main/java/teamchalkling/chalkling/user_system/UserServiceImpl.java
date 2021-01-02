package teamchalkling.chalkling.user_system;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
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
     * Sets the current user logged in
     * @param userName  String  the username of the user
     */
    @Override
    public void setCurrentUser(String userName){
        currentUserEntity = getUserByUsername(userName);
    }

    /**
     * Gets the current user's ID, that is logged in
     * @return UserEntity the UserEntity that currently logged in
     */
    // TODO: Return user?
    @Override
    public UserEntity getCurrentUser(){
        if (currentUserEntity != null){
            return currentUserEntity;
        }
        return null;
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
    public boolean canLogin(String username, String salt, String hash) {
        if (!userExists(username)) return false;
        else {
            UserEntity user = getUserByUsername(username);
            return (salt.equals(user.getSalt()) && hash.equals(user.getHash()));
        }
    }

    @Override
    public int getUserIdByUserName(String username) {
        if (!userExists(username)) return -1; // user DNE
        else return getUserByUsername(username).getUserId();
    }

    @Override
    public UserEntity getUserByUserId(int userId) {
        Optional<UserEntity> temp = user_repository.findById(userId);
        return temp.orElse(null);
    }

//    /**
//     * Add friend into login user's friend list
//     * @param   username1  String  the username of the friend the user wants to add
//     * @param   username2  String  target user to add to their friends' list
//     * @return             boolean  True if add successfully, otherwise false
//     */
//    // TODO: Fix later, Should it be username or userID?
//    @Override
//    public boolean addFriend(String username1, String username2){
//        UserEntity userEntity1 = getUserByUsername(username1);
//        UserEntity userEntity2 = getUserByUsername(username2);
//        if (userEntity1 == null || userEntity2 == null) return false;
//        else if (userEntity1.isFriend(userEntity2.getUserId())) return false; // already friend
//        else {
//            userEntity1.addFriend(userEntity2.getUserId());
//            user_repository.save(userEntity1);
//            return true;
//        }
//    }
//
//    /**
//     * Remove friend from the login user's friend list
//     * @param   username1  String  the username of the friend the user wants to remove
//     * @param   username2  String  target user to remove from their friends' list
//     * @return             boolean  True if remove successfully, otherwise false
//     */
//    // TODO: Should it be username or userID?
//    @Override
//    public boolean removeFriend(String username1, String username2){
//        UserEntity userEntity1 = getUserByUsername(username1);
//        UserEntity userEntity2 = getUserByUsername(username2);
//        if (userEntity1 == null || userEntity2 == null) return false;
//        else if (!userEntity1.isFriend(userEntity2.getUserId())) return false; // already not friend
//        else {
//            userEntity1.removeFriend(userEntity2.getUserId());
//            user_repository.save(userEntity1);
//            return true;
//        }
//    }

    /*
    Helper function to find the user in the system
     */
    private UserEntity getUserByUsername(String username){
        Optional<UserEntity> temp = user_repository.findByUsername(username);
        return temp.orElse(null);
    }

}
