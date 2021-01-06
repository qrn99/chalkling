package com.chalkling.group_system;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GroupEntity")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "groupId")
    private int groupId;

    @Column(name = "hostId")
    private int hostId;

    @Column(name = "groupName")
    private String groupName;

    @Type(type = "list-array")
    @Column(name = "channelIdList", columnDefinition = "integer[]")
    private List<Integer> channelIdList;

    @ElementCollection
    private List<Integer> memberList;

    public GroupEntity() {}
    public GroupEntity(int hostId, String groupName) {
        this.hostId = hostId;
        this.groupName = groupName;
        this.channelIdList = new ArrayList<>();
        this.memberList = new ArrayList<>();
    }

    public int getGroupId() {
        return groupId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Integer> getChannelIdList() {
        return channelIdList;
    }


    public List<Integer> getMemberList() {
        return memberList;
    }
}
