package com.chalkling.message_system;

public class GetMessageJSON {
    private String messageType;
    private String receiverName;

    public GetMessageJSON(String messageType, String receiverName) {
        this.messageType = messageType;
        this.receiverName = receiverName;
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
}
