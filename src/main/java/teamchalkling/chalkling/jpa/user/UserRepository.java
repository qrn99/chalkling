package teamchalkling.chalkling.jpa.user;

import teamchalkling.chalkling.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {
    /**
     * Returns the found User entry by using its username as search
     * criteria.
     *
     * @param username  String  the username of the user
     * @return          User    If no user entry is found, this method returns null.
     */
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);



}
