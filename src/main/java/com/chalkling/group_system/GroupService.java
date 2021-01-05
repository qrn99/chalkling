package com.chalkling.group_system;

public interface GroupService {

    void addGroup(String username, String groupName);
    void removeGroup(String username, String groupName);
    void addGroupMember(String memberName, String groupName);
    void removeGroupMember(String memberName, String groupName);

    boolean isGroupMember(String memberName, String groupName);
}
