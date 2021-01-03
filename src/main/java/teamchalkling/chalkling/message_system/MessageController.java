package teamchalkling.chalkling.message_system;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teamchalkling.chalkling.user_system.UserService;

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
    public MessageJSON getConversation(@RequestBody GetMessageJSON getMessageJSON) {
        String senderName = getMessageJSON.getSenderName();
        String receiverName = getMessageJSON.getReceiverName();
        if (!userService.userExists(senderName) || !userService.userExists(receiverName)) {
            return null;
        }
        int senderId = userService.getUserIdByUserName(senderName);
        int receiverId = userService.getUserIdByUserName(receiverName);
        List<MessageEntity> messages = messageService.findConversation(senderId, receiverId, getMessageJSON.getMessageType());
        return new MessageJSON(messages);
    }


    @PutMapping(value = "/api/message", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody AddMessageJSON addMessageJSON) {
        String senderName = addMessageJSON.getSenderName();
        String receiverName = addMessageJSON.getReceiverName();
        if (!userService.userExists(senderName) || !userService.userExists(receiverName)) {
            return;
        }
        int senderId = userService.getUserIdByUserName(addMessageJSON.getSenderName());
        int receiverId = userService.getUserIdByUserName(addMessageJSON.getReceiverName());
        messageService.addMessage(addMessageJSON.getMessageType(), senderId, receiverId, addMessageJSON.getContent());

    }

}
