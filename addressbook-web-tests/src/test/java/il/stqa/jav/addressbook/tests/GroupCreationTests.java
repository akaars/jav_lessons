package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupLink();
    List<GroupForm>  before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupForm("test222", null, null));
    List<GroupForm>  after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    GroupForm group = new GroupForm(after.get(before.size()).getGroupId(),"test222", null, null);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
