package com.chalkling.group_system;

import javax.persistence.*;

@Entity
@Table(name = "ChannelEntity")
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "channelId")
    private int channelId;

    @Column(name = "channelName")
    private String channelName;

    // TODO: change groupId to GroupEntity
    @Column(name = "groupId")
    private int groupId;

    public ChannelEntity() {}
    public ChannelEntity(String channelName, int groupId) {
        this.channelName = channelName;
        this.groupId = groupId;
    }
}
