package teamchalkling.chalkling.message_system;

import javax.inject.Inject;
import java.util.List;

public class MessageServiceImpl implements MessageService{
    private final MessageRepository message_repository;

    @Inject
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.message_repository = messageRepository;
    }

    public List<MessageEntity> findConversation(int senderId, int receiverId, MessageType messageType) {
        return message_repository.findBySenderIdAndReceiverIdAndMessageType(senderId, receiverId, messageType);
    }

    public void addMessage(MessageType messageType, int senderId, int receiverId, String content) {
        message_repository.save(new MessageEntity(messageType, senderId, receiverId, content));
    }

}
