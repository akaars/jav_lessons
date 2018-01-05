package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupModifyTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupLink();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupForm().withName("test1"));
    }
  }

  @Test
  public void testGroupUpdate(){
    Set<GroupForm> before=app.group().all();
    GroupForm updateGroup = before.iterator().next();
    GroupForm group = new GroupForm().withId(updateGroup.getGroupId()).withName("test2").withHeader("test6").
            withFooter("test7");
    app.group().updateGroup(group);
    Set<GroupForm> after=app.group().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(updateGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
