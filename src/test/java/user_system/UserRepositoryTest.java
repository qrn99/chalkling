package user_system;

import com.chalkling.config.PersistenceContext;
import com.chalkling.user_system.UserEntity;
import com.chalkling.user_system.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UserRepository.class))
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private UserEntity user1;
    private UserEntity user2;

    @Before
    public void setUp() {
        user1 = new UserEntity("user1", "$2a$10$vJ7jWUxKCI7stLVtgtpszO", "$2a$10$vJ7jWUxKCI7stLVtgtpszOnq91hVZttt9TbQ401fjqdz9ct5iW0Ju");
        user2 = new UserEntity("user2", "$2a$10$Fxh.tTBv2V3MikJTIXB2.O", "$2a$10$Fxh.tTBv2V3MikJTIXB2.OlN324mu9cvl.ceKpXwAP/cTFU2Bf3UG");
        userRepository.save(user1);
        userRepository.save(user2);
    }

    // Test BaseRepository
    @Test
    public void testRead(){
        List<UserEntity> userEntities = userRepository.findAll();
        assertEquals(2, userRepository.findAll().size());
    }

    @Test
    public void testSave(){
        UserEntity Jan = new UserEntity("Jan", "$2a$10$vJ7jWUxKCI7stLVtgtpszO", "$2a$10$vJ7jWUxKCI7stLVtgtpszOnq91hVZttt9TbQ401fjqdz9ct5iW0Ju");
        userRepository.save(Jan);
        assertEquals(3, userRepository.findAll().size());

        Optional<UserEntity> found_Jan = userRepository.findByUsername("Jan");
        assertNotNull(found_Jan);
        //User Jan is found
        assertEquals(Jan, found_Jan.get());
    }

    @Test
    public void testFindById(){
        assertEquals(user1, userRepository.findById(user1.getUserId()).get());
    }

    // Test UserRepository
    @Test
    public void testFindByUsername(){
        Optional<UserEntity> found_user1 = userRepository.findByUsername("user1");
        assertNotNull(found_user1);
        //found_user1 is not null
        assertEquals(user1, found_user1.get());

        Optional<UserEntity> DNE_user = userRepository.findByUsername("DNE");
        assertFalse(DNE_user.isPresent());
    }
//    // TODO: Fix later
//    @Test
//    public void testFriendList(){
//        UserEntity userEntity1 = userRepository.findByUsername("user1").get();
//        assertEquals(new ArrayList<>(), userEntity1.getFriendList());
//    }
}
