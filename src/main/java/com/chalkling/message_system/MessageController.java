package com.chalkling.message_system;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.chalkling.user_system.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }


    @PostMapping(value = "/api/message", consumes = "application/json", produces = "application/json")
    public MessageJSON getConversation(@RequestBody GetMessageJSON getMessageJSON, HttpServletRequest request) {
        String senderName = userService.getCurrentUser(request);
        String receiverName = getMessageJSON.getReceiverName();
        if (!userService.userExists(senderName) || !userService.userExists(receiverName)) {
            return null;
        }
        int senderId = userService.getUserIdByUserName(senderName);
        int receiverId = userService.getUserIdByUserName(receiverName);
        List<MessageEntity> messages = messageService.findConversation(senderId, receiverId, getMessageJSON.getMessageType());
        return new MessageJSON(messages, senderName, receiverName);
    }


    @PutMapping(value = "/api/message", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody AddMessageJSON addMessageJSON, HttpServletRequest request) {
        String senderName = userService.getCurrentUser(request);
        String receiverName = addMessageJSON.getReceiverName();
        if (!userService.userExists(senderName) || !userService.userExists(receiverName)) {
            return;
        }
        int senderId = userService.getUserIdByUserName(senderName);
        int receiverId = userService.getUserIdByUserName(receiverName);
        messageService.addMessage(addMessageJSON.getMessageType(), senderId, receiverId, addMessageJSON.getContent());

    }

}
