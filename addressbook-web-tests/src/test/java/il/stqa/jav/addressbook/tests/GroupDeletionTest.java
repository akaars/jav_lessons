/**
 * Created by ilya on 12/3/17
 */
package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupLink();
    if (! app.getGroupHelper().isGroupExists()) {
      app.getGroupHelper().createGroup(new GroupForm("test1", null, null));
    }
    app.getNavigationHelper().selectFirstAvailableCheckbox();
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
  }
}
