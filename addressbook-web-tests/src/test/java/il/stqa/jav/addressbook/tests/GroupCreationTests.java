package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupLink();
    app.getGroupHelper().addNewGroup();
    app.getGroupHelper().fillGroupForm(new GroupForm("test1", "test2", "test3"));
    app.getNavigationHelper().submit();
    app.getGroupHelper().returnToGroupPage();
  }

}
