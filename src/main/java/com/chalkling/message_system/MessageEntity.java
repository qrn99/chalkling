package com.chalkling.message_system;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "MessageEntity")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "messageId")
    private int messageId;

    @Column(name = "time")
    private String time;

    @Column(name = "messageType")
    private MessageType messageType;

    @Column(name = "senderId")
    private int senderId;

    @Column(name = "receiverId")
    private int receiverId;

    @Column(name = "content")
    private String content;

    public MessageEntity() {}
    public MessageEntity(MessageType messageType, int senderId, int receiverId, String content) {
        this.messageType = messageType;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;

        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.time = localTime.format(format);
   }

    public int getMessageId() {
        return messageId;
    }

    public String getTime() {
        return time;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
