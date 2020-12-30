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
        userRepository.save(new User("user1", "pass1"));
        userRepository.save(new User("user2", "pass2"));
    }

    @Test
    public void testFindByUsername(){
        Optional<User> user1 = userRepository.findByUsername("user1");
        assertTrue(user1.isPresent());
        //user is found
        User actual_user1 = user1.get();
        assertEquals("user1", actual_user1.getUsername());
        assertEquals("pass1", actual_user1.getPassword());

        Optional<User> user = userRepository.findByUsername("DNE");
        assertFalse(user.isPresent());
    }

    @Test
    public void testRead(){
        List<User> users = userRepository.findAll();
        assertEquals(2, userRepository.findAll().size());
    }

    @Test
    public void testSave(){
        User user = new User("Jan", "passJan");
        userRepository.save(user);
        assertEquals(3, userRepository.findAll().size());

        Optional<User> Jan = userRepository.findByUsername("Jan");
        assertTrue(Jan.isPresent());
        //Jan is found
        User actual_Jan = Jan.get();
        assertEquals("Jan", actual_Jan.getUsername());
        assertEquals("passJan", actual_Jan.getPassword());
    }

    @Test
    public void testFriendList(){
        User user1 = userRepository.findByUsername("user1").get();
        assertEquals(new ArrayList<>(), user1.getFriendList());
    }
}
