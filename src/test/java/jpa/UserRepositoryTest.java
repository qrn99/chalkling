package jpa;

import teamchalkling.chalkling.jpa.user.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {teamchalkling.chalkling.jpa.config.PersistenceContext.class})
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UserRepository.class))
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.save(new UserEntity("user1", "$2a$10$vJ7jWUxKCI7stLVtgtpszO", "$2a$10$vJ7jWUxKCI7stLVtgtpszOnq91hVZttt9TbQ401fjqdz9ct5iW0Ju"));
        userRepository.save(new UserEntity("user2", "$2a$10$Fxh.tTBv2V3MikJTIXB2.O", "$2a$10$Fxh.tTBv2V3MikJTIXB2.OlN324mu9cvl.ceKpXwAP/cTFU2Bf3UG"));
    }

    @Test
    public void testFindByUsername(){
        Optional<UserEntity> user1 = userRepository.findByUsername("user1");
        assertTrue(user1.isPresent());
        //user is found
        UserEntity actual_userEntity1 = user1.get();
        assertEquals("user1", actual_userEntity1.getUsername());

        Optional<UserEntity> user = userRepository.findByUsername("DNE");
        assertFalse(user.isPresent());
    }

    @Test
    public void testRead(){
        List<UserEntity> userEntities = userRepository.findAll();
        assertEquals(2, userRepository.findAll().size());
    }

    @Test
    public void testSave(){
        UserEntity userEntity = new UserEntity("Jan", "$2a$10$vJ7jWUxKCI7stLVtgtpszO", "$2a$10$vJ7jWUxKCI7stLVtgtpszOnq91hVZttt9TbQ401fjqdz9ct5iW0Ju");
        userRepository.save(userEntity);
        assertEquals(3, userRepository.findAll().size());

        Optional<UserEntity> Jan = userRepository.findByUsername("Jan");
        assertTrue(Jan.isPresent());
        //Jan is found
        UserEntity actual_Jan = Jan.get();
        assertEquals("Jan", actual_Jan.getUsername());
    }

//    // TODO: Fix later
//    @Test
//    public void testFriendList(){
//        UserEntity userEntity1 = userRepository.findByUsername("user1").get();
//        assertEquals(new ArrayList<>(), userEntity1.getFriendList());
//    }
}
