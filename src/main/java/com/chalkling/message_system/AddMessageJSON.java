package com.chalkling.message_system;

public class AddMessageJSON {
    private String messageType;
    private String receiverName;
    private String content;

    public AddMessageJSON(String messageType, String receiverName, String content) {
        this.messageType = messageType;
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
