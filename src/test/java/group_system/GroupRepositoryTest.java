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

import java.util.*;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@DataJpaTest(properties = {"spring.test.database.replace=NONE", "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres"},
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = GroupRepository.class))
public class GroupRepositoryTest {
    @Autowired
    private GroupRepository groupRepository;

    private GroupEntity group1;
    private GroupEntity group2;

    @Before
    public void setUp() {
        this.group1 = new GroupEntity(1, "group1");
        group1.getMemberList().add(2);
        this.group2 = new GroupEntity(2, "group2");
        groupRepository.save(group1);
        groupRepository.save(group2);
    }

    @Test
    public void testFindByGroupName(){
        Optional<GroupEntity> found_group1 = groupRepository.findByGroupName("group1");
        Optional<GroupEntity> found_group2 = groupRepository.findByGroupName("group2");
        assertNotNull(group1);
        assertEquals(group1, found_group1.get());
        assertNotNull(group2);
        assertEquals(group2, found_group2.get());

        Optional<GroupEntity> DNE_group = groupRepository.findByGroupName("DNE");
        assertFalse(DNE_group.isPresent());
    }

    @Test
    public void testFindGroupsByUserId(){
        List<GroupEntity> expected_groups_for_id1 = new ArrayList<>(Collections.singletonList(group1));
        List<GroupEntity> findGroups_for_id1 = groupRepository.findGroupsByUserId(1);
        assertEquals(expected_groups_for_id1, findGroups_for_id1);

        List<GroupEntity> expected_groups_for_id2 = new ArrayList<>(Arrays.asList(group1, group2));
        List<GroupEntity> findGroups_for_id2 = groupRepository.findGroupsByUserId(2);
        assertEquals(expected_groups_for_id2, findGroups_for_id2);
    }
}
