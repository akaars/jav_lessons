package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModifyTest extends TestBase {
  @Test
  public void testGroupUpdate(){
    app.getNavigationHelper().gotoGroupLink();
    if (! app.getGroupHelper().isGroupExists()) {
      app.getGroupHelper().createGroup(new GroupForm("test1", null, null));
    }
    List<GroupForm> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupUpdate();
    GroupForm group = new GroupForm(before.get(before.size() - 1).getGroupId(),"test2", "test6",
            "test7");
    app.getGroupHelper().fillGroupForm(group);
    app.getNavigationHelper().update();
    app.getGroupHelper().returnToGroupPage();
    List<GroupForm>  after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size() - 1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
