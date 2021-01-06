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

    @Test
    public void testRemoveGroup(){
        groupService.removeGroup("group1");
        assertEquals(1, groupRepository.findAll().size());
    }

    @Test
    public void testAddMember(){
        List<Integer> expected_member_list1 = new ArrayList<>();
        assertEquals(expected_member_list1, group1.getMemberList());

        assertTrue(groupService.addMember(2, "group1"));
        // duplicate adding
        assertFalse(groupService.addMember(2, "group1"));

        expected_member_list1.add(2);
        assertEquals(expected_member_list1, group1.getMemberList());
    }

    @Test
    public void testRemoveMember(){
        List<Integer> expected_member_list1 = new ArrayList<>();
        assertEquals(expected_member_list1, group1.getMemberList());
        assertTrue(groupService.addMember(2, "group1"));
        assertTrue(groupService.addMember(-10, "group1"));
        expected_member_list1.add(2);
        expected_member_list1.add(-10);
        assertEquals(expected_member_list1, group1.getMemberList());

        assertTrue(groupService.removeMember(2, "group1"));
        // duplicate removing
        assertFalse(groupService.removeMember(2, "group1"));
        assertTrue(groupService.removeMember(-10, "group1"));

        assertEquals(new ArrayList<>(), group1.getMemberList());
    }

    @Test
    public void testGroupExists(){
        assertTrue(groupService.groupExists("group1"));
        assertFalse(groupService.groupExists("DNE"));
    }

    @Test
    public void testGetGroupByGroupId(){
        assertEquals(group1, groupService.getGroupByGroupId(group1.getGroupId()));
        assertNull(groupService.getGroupByGroupId(-1));
    }

    @Test
    public void testGetGroupNameByGroupId(){
        assertEquals(group1.getGroupName(), groupService.getGroupNameByGroupId(group1.getGroupId()));
        assertEquals("", groupService.getGroupNameByGroupId(-1));
    }

    @Test
    public void testGetGroupIdByGroupName(){
        assertEquals(group1.getGroupId(), groupService.getGroupIdByGroupName(group1.getGroupName()));
        assertEquals(-1, groupService.getGroupIdByGroupName("DNE"));
    }
}