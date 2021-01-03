package message_system;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import teamchalkling.chalkling.message_system.*;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {teamchalkling.chalkling.config.PersistenceContext.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MessageRepository.class))
public class MessageServiceTest {
    @Autowired private MessageRepository messageRepository;

    private MessageService messageService;

    private MessageEntity message1;
    private MessageEntity message2;
    private MessageEntity message3;
    private MessageEntity message4;

    @Before
    public void setup(){
        message1 = new MessageEntity(MessageType.DIRECT, 0, 1, "hi userid=1");
        message2 = new MessageEntity(MessageType.DIRECT, 1, 0, "hi userid=0");
        message3 = new MessageEntity(MessageType.DIRECT, 0, 1, "hi again userid=1");
        message4 = new MessageEntity(MessageType.DIRECT, 1, 0, "hi again userid=0");
        MessageEntity random1 = new MessageEntity(MessageType.DIRECT, 0, 10, "random1");
        MessageEntity random2 = new MessageEntity(MessageType.DIRECT, 10, 0, "random2");
        messageRepository.save(message1);
        messageRepository.save(message2);
        messageRepository.save(message3);
        messageRepository.save(message4);
        messageRepository.save(random1);
        messageRepository.save(random2);
        messageService = new MessageServiceImpl(messageRepository);
    }

    @Test
    public void testAddMessage(){
        messageService.addMessage(MessageType.DIRECT, 0, 1, "new message from userid=0 to userid=1");
        assertEquals(7, messageRepository.findAll().size());
    }

    @Test
    public void testFindConversation(){
        List<MessageEntity> messages = messageService.findConversation(0, 1, MessageType.DIRECT);
        assertEquals(4, messages.size());

        List<MessageEntity> actual_message_list = new ArrayList<>();
        actual_message_list.add(message1);
        actual_message_list.add(message2);
        actual_message_list.add(message3);
        actual_message_list.add(message4);
        assertTrue(messages.containsAll(actual_message_list));
        assertTrue(actual_message_list.containsAll(messages));
    }

}
