/**
 * Created by ilya on 12/3/17
 */
package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupLink();
    if (! app.getGroupHelper().isGroupExists()) {
      app.getGroupHelper().createGroup(new GroupForm("test1", null, null));
    }
    List<GroupForm> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupForm>  after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(before.size() - 1);
    for (int i=0; i < after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }
    Assert.assertEquals(before, after);
  }
}
