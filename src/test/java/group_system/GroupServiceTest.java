package group_system;

import com.chalkling.config.PersistenceContext;
import com.chalkling.group_system.*;

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
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@DataJpaTest(properties = {"spring.test.database.replace=NONE", "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres"},
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = GroupRepository.class))
public class GroupServiceTest{
    @Autowired private GroupRepository groupRepository;

    private GroupService groupService;
    private GroupEntity group1;
    private GroupEntity group2;

    @Before
    public void setUp() {
        this.group1 = new GroupEntity(1, "group1");
        this.group2 = new GroupEntity(2, "group2");
        groupRepository.save(group1);
        groupRepository.save(group2);
        this.groupService = new GroupServiceImpl(groupRepository);
    }

    @Test
    public void testAddGroup(){
        groupService.addGroup(1, "group3");
        assertEquals(3, groupRepository.findAll().size());
    }

}