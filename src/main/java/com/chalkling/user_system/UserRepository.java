package com.chalkling.user_system;

import com.chalkling.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Integer> {
    /**
     * Returns the found UserEntity entry by using its username as search
     * criteria.
     *
     * @param username  String  the username of the user
     * @return          UserEntity    If no user entry is found, this method returns null.
     */
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findByUsername(@Param("username") String username);



}
