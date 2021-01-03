package com.chalkling.message_system;

public class AddMessageJSON {
    private String messageType;
    private String senderName;
    private String receiverName;
    private String content;

    public AddMessageJSON(String messageType, String senderName, String receiverName, String content) {
        this.messageType = messageType;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.content = content;
    }

    public MessageType getMessageType() {
        if (messageType.equals("DIRECT")) {
            return MessageType.DIRECT;
        } else if (messageType.equals("CHANNEL")) {
            return MessageType.CHANNEL;
        }
        return null;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
