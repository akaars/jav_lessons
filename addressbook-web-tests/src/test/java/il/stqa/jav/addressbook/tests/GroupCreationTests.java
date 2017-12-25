package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupLink();
    app.getGroupHelper().createGroup(new GroupForm("test1", null, null));
  }

}
