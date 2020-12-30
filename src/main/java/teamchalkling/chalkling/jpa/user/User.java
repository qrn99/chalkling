package teamchalkling.chalkling.jpa.user;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
        @Id
        @Column(name = "UserId")
        private int userId;

        @Column(name = "Username")
        private String username;

        @Column(name = "Password")
        private String password;

        // TODO: double check if PostgreSQL has array type
        @ElementCollection
        @Column(name = "FriendList")
        private List<Integer> friendList;

        private static int id_counter = 0;

        public User() {}
        public User(String username, String password) {
                this.username = username;
                this.password = password;
                this.userId = id_counter;
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
        public int getUserId() {
                return userId;
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
