package teamchalkling.chalkling.message_system;

import java.util.List;

public interface MessageService {
    List<MessageEntity> findConversation(int senderId, int receiverId, MessageType messageType);
    void addMessage(MessageType messageType, int senderId, int receiverId, String content);
}
