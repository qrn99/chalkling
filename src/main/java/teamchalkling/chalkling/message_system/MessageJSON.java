package teamchalkling.chalkling.message_system;

import java.util.List;

public class MessageJSON {
    private List<MessageEntity> messages;
    public MessageJSON(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public class MessageEntityJSON {
        private MessageEntity messageEntity;
        public MessageEntityJSON(MessageEntity messageEntity) {
            this.messageEntity = messageEntity;
        }

        public MessageEntity getMessageEntity() {
            return messageEntity;
        }

        public void setMessageEntity(MessageEntity messageEntity) {
            this.messageEntity = messageEntity;
        }
    }
}
