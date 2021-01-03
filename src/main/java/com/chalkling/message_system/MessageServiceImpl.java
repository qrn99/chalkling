package com.chalkling.message_system;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    private final MessageRepository message_repository;

    @Inject
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.message_repository = messageRepository;
    }

    @Override
    public List<MessageEntity> findConversation(int senderId, int receiverId, MessageType messageType) {
        // TODO: channel messages requires overloading
        List<MessageEntity> forward = message_repository.findBySenderIdAndReceiverIdAndMessageType(senderId, receiverId, messageType);
        List<MessageEntity> backward = message_repository.findBySenderIdAndReceiverIdAndMessageType(receiverId, senderId, messageType);
        forward.addAll(backward);
        return forward;
    }

    @Override
    public void addMessage(MessageType messageType, int senderId, int receiverId, String content) {
        message_repository.save(new MessageEntity(messageType, senderId, receiverId, content));
    }

}
