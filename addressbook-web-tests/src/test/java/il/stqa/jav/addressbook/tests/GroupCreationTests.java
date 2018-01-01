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
    GroupForm group = new GroupForm("test225", null, null);

    app.getGroupHelper().createGroup(group);
    List<GroupForm>  after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    group.setGroupId(after.stream().max((o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId())).get().getGroupId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
