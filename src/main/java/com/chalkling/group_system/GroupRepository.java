package com.chalkling.group_system;

import com.chalkling.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends BaseRepository<GroupEntity, Integer> {
    /**
     * Returns the found GroupEntity entry by using its group name as search
     * criteria.
     *
     * @param groupName  String   the name of the group
     * @return           Optional<GroupEntity>
     */
    @Query("SELECT u FROM GroupEntity u WHERE u.groupName = :groupName")
    Optional<GroupEntity> findByGroupName(@Param("groupName") String groupName);
}
