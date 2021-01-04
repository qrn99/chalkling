package message_system;

import com.chalkling.config.PersistenceContext;
import com.chalkling.message_system.*;
import com.chalkling.user_system.UserEntity;
import com.chalkling.user_system.UserRepository;
import com.chalkling.user_system.UserService;
import com.chalkling.user_system.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@DataJpaTest(properties = {"spring.test.database.replace=NONE", "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {MessageRepository.class, UserRepository.class}))
public class MessageControllerTest {

    private MessageController messageController;
    private UserService userService;
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;


    private UserEntity user1;
    private UserEntity user2;
    private UserEntity user3;


    private AddMessageJSON message1;
    private AddMessageJSON message2;
    private AddMessageJSON message3;

    private GetMessageJSON getMessage1;
    private GetMessageJSON getMessage2;

    private MockHttpServletRequest request;

    @Before
    public void setUp(){
        message1 = new AddMessageJSON("DIRECT", "user2", "msg1");
        message2 = new AddMessageJSON("DIRECT", "user3", "msg2");
        message3 = new AddMessageJSON("DIRECT", "user3", "msg3");


        getMessage1 = new GetMessageJSON("DIRECT", "user2");
        getMessage2 = new GetMessageJSON("DIRECT", "user3");

        user1 = new UserEntity("user1", "$2a$10$vJ7jWUxKCI7stLVtgtpszO", "$2a$10$vJ7jWUxKCI7stLVtgtpszOnq91hVZttt9TbQ401fjqdz9ct5iW0Ju");
        user2 = new UserEntity("user2", "$2a$10$Fxh.tTBv2V3MikJTIXB2.O", "$2a$10$Fxh.tTBv2V3MikJTIXB2.OlN324mu9cvl.ceKpXwAP/cTFU2Bf3UG");
        user3 = new UserEntity("user3", "$2a$10$5wS1RHT.rFVyhlNowhD/Fu", "$2a$10$5wS1RHT.rFVyhlNowhD/FuX6OHfvgW0lo6AQB4UEmodWPPmFC8qIC");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        userService = new UserServiceImpl(userRepository);
        messageService = new MessageServiceImpl(messageRepository);
        messageController = new MessageController(messageService, userService);

        request = new MockHttpServletRequest("POST", "/api/login");
        request.setContentType("application/json");
    }

    @Test
    public void testSendMessage(){
        userService.setCurrentUser(request, user1.getUsername());
        messageController.sendMessage(message1, request);
        userService.setCurrentUser(request, user2.getUsername());
        messageController.sendMessage(message2, request);
        messageController.sendMessage(message3, request);

        List<MessageEntity> messages = messageRepository.findBySenderIdAndReceiverIdAndMessageType(user1.getUserId(), user2.getUserId(), MessageType.DIRECT);
        assertEquals(1, messages.size());
        assertEquals(messages.get(0).getSenderId(), user1.getUserId());
        assertEquals(messages.get(0).getReceiverId(), user2.getUserId());
        assertEquals("msg1", messages.get(0).getContent());

        messages = messageRepository.findBySenderIdAndReceiverIdAndMessageType(user2.getUserId(), user3.getUserId(), MessageType.DIRECT);
        assertEquals(2, messages.size());
        assertEquals(messages.get(0).getSenderId(), user2.getUserId());
        assertEquals(messages.get(0).getReceiverId(), user3.getUserId());
        assertEquals("msg2", messages.get(0).getContent());
        assertEquals(messages.get(1).getSenderId(), user2.getUserId());
        assertEquals(messages.get(1).getReceiverId(), user3.getUserId());
        assertEquals("msg3", messages.get(1).getContent());
    }

    @Test
    public void testGetConversation(){
        userService.setCurrentUser(request, user1.getUsername());
        messageController.sendMessage(message1, request);


        MessageJSON messageJSON1 = messageController.getConversation(getMessage1, request);
        assertEquals(messageJSON1.getMessages().get(0).getSenderName(), user1.getUsername());
        assertEquals(messageJSON1.getMessages().get(0).getReceiverName(), user2.getUsername());
        assertEquals("msg1", messageJSON1.getMessages().get(0).getContent());

        userService.setCurrentUser(request, user2.getUsername());
        messageController.sendMessage(message2, request);
        messageController.sendMessage(message3, request);

        MessageJSON messageJSON2 = messageController.getConversation(getMessage2, request);


        assertEquals(messageJSON2.getMessages().get(0).getSenderName(), user2.getUsername());
        assertEquals(messageJSON2.getMessages().get(0).getReceiverName(), user3.getUsername());
        assertEquals("msg2", messageJSON2.getMessages().get(0).getContent());

        assertEquals(messageJSON2.getMessages().get(1).getSenderName(), user2.getUsername());
        assertEquals(messageJSON2.getMessages().get(1).getReceiverName(), user3.getUsername());
        assertEquals("msg3", messageJSON2.getMessages().get(1).getContent());


    }

}
