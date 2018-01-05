package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.goTo().groupLink();
    Set<GroupForm> before=app.group().all();
    GroupForm group = new GroupForm().withName("test225");

    app.group().create(group);
    Set<GroupForm>after=app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    group.withId(after.stream().mapToInt((g)->g.getGroupId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
