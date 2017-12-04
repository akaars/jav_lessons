/**
 * Created by ilya on 12/3/17
 */
package il.stqa.jav.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupLink();
    app.getNavigationHelper().selectFirstAvailableCheckbox();
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
  }
}
