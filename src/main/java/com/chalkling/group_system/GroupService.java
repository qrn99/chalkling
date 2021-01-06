package com.chalkling.group_system;

import java.util.List;

public interface GroupService {

    void addGroup(int hostId, String groupName);

    void removeGroup(String groupName);

    boolean addMember(int memberId, String groupName);

    boolean removeMember(int memberId, String groupName);

    boolean groupExists(String groupName);

    GroupEntity getGroupByGroupId(int groupId);

    String getGroupNameByGroupId(int groupId);

    int getGroupIdByGroupName(String groupName);

//    List<GroupEntity> findGroups(int userId);
}
