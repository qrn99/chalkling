package com.chalkling.group_system;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{
    private final GroupRepository group_repository;

    @Inject
    public GroupServiceImpl(GroupRepository groupRepository){
        this.group_repository = groupRepository;
    }


    @Override
    public void addGroup(int hostId, String groupName) {
        group_repository.save(new GroupEntity(hostId, groupName));
    }

    @Override
    public void removeGroup(String groupName) {
        group_repository.delete(findGroupByGroupName(groupName));
    }

    @Override
    public boolean addMember(int memberId, String groupName) {
        GroupEntity group = findGroupByGroupName(groupName);
        List<Integer> memberList = group.getMemberList();
        if (memberList.contains(memberId)) return false;
        else {
            memberList.add(memberId);
            group_repository.save(group);
            return true;
        }
    }

    @Override
    public boolean removeMember(int memberId, String groupName) {
        GroupEntity group = findGroupByGroupName(groupName);
        List<Integer> memberList = group.getMemberList();
        if (!memberList.contains(memberId)) return false;
        else {
            memberList.remove((Integer) memberId);
            group_repository.save(group);
            return true;
        }
    }

    @Override
    public boolean groupExists(String groupName) {
        return this.findGroupByGroupName(groupName) != null;
    }

    @Override
    public GroupEntity getGroupByGroupId(int groupId) {
        Optional<GroupEntity> temp = group_repository.findById(groupId);
        return temp.orElse(null);
    }

    @Override
    public String getGroupNameByGroupId(int groupId) {
        GroupEntity group = this.getGroupByGroupId(groupId);
        if (group == null) return "";
        else return group.getGroupName();
    }

    @Override
    public int getGroupIdByGroupName(String groupName) {
        GroupEntity group = this.findGroupByGroupName(groupName);
        if (group == null) return -1;
        else return group.getGroupId();
    }

//    @Override
//    public List<GroupEntity> findGroups(int userId) {
//        return group_repository.findGroupsByUserId(userId);
//    }

    // Helper Function

    /*
     * Returns the found GroupEntity entry by using its group name as search
     * criteria.
     * @param groupName  String         the name of the group
     * @return           GroupEntity    If no group entry is found, this method returns null.
     */
    private GroupEntity findGroupByGroupName(String groupName){
        Optional<GroupEntity> temp = group_repository.findByGroupName(groupName);
        return temp.orElse(null);
    }

    /*
     * Check if member is in the group
     */
    private boolean isGroupMember(int memberId, String groupName) {
        GroupEntity group = this.findGroupByGroupName(groupName);
        return group.getMemberList().contains(memberId);
    }

}
