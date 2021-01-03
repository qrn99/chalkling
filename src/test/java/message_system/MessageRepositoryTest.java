package message_system;

import com.chalkling.config.PersistenceContext;
import com.chalkling.message_system.MessageEntity;
import com.chalkling.message_system.MessageRepository;
import com.chalkling.message_system.MessageType;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MessageRepository.class))
public class MessageRepositoryTest {
    @Autowired private MessageRepository messageRepository;

    private MessageEntity message1;
    private MessageEntity message2;

    @Before
    public void setUp() {
        // make up sender and receiver id
        message1 = new MessageEntity(MessageType.DIRECT, 0, 1, "hi userid=1");
        message2 = new MessageEntity(MessageType.DIRECT, 1, 0, "hi userid=0");
        messageRepository.save(message1);
        messageRepository.save(message2);
    }

    @Test
    public void testFindBySenderIdAndReceiverIdAndMessageType(){
        List<MessageEntity> messages = messageRepository.findBySenderIdAndReceiverIdAndMessageType(0, 1, MessageType.DIRECT);
        assertEquals(1, messages.size());
        assertEquals(message1, messages.get(0));

        List<MessageEntity> reverse_messages = messageRepository.findBySenderIdAndReceiverIdAndMessageType(1, 0, MessageType.DIRECT);
        assertEquals(1, reverse_messages.size());
        assertEquals(message2, reverse_messages.get(0));

        List<MessageEntity> empty = messageRepository.findBySenderIdAndReceiverIdAndMessageType(-1, -2, MessageType.DIRECT);
        assertEquals(new ArrayList<>(), empty);
    }
}
