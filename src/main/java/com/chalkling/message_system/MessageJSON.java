package com.chalkling.message_system;

import java.util.ArrayList;
import java.util.List;

public class MessageJSON {
    private List<MessagesWithUsername> messagesWithUsername;
    public MessageJSON(List<MessageEntity> messages, String senderName, String receiverName) {
        messagesWithUsername = new ArrayList<>();
        for (MessageEntity message: messages) {
            messagesWithUsername.add(new MessagesWithUsername(message, senderName, receiverName));
        }
    }

    public List<MessagesWithUsername> getMessages() {
        return messagesWithUsername;
    }

    public void setMessages(List<MessagesWithUsername> messagesWithUsername) {
        this.messagesWithUsername = messagesWithUsername;
    }

    public class MessagesWithUsername {
        private int messageId;
        private String time;
        private MessageType messageType;
        private String senderName;
        private String receiverName;
        private String content;

        public MessagesWithUsername(MessageEntity messages, String senderName, String receiverName) {
            messageId = messages.getMessageId();
            time = messages.getTime();
            messageType = messages.getMessageType();
            content = messages.getContent();

            this.senderName = senderName;
            this.receiverName = receiverName;
        }

        public int getMessageId() {
            return messageId;
        }

        public void setMessageId(int messageId) {
            this.messageId = messageId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public MessageType getMessageType() {
            return messageType;
        }

        public void setMessageType(MessageType messageType) {
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
}
