package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.annotations.Test;

public class GroupModifyTest extends TestBase {
  @Test
  public void testGroupUpdate(){
    app.getNavigationHelper().gotoGroupLink();
    if (! app.getGroupHelper().isGroupExists()) {
      app.getGroupHelper().createGroup(new GroupForm("test1", "test6", "test7"));
    }
    app.getGroupHelper().selectFirstAvailableCheckbox();
    app.getGroupHelper().initGroupUpdate();
    app.getGroupHelper().fillGroupForm(new GroupForm("test1", "test6", "test7"));
    app.getNavigationHelper().update();
    app.getGroupHelper().returnToGroupPage();
  }
}
