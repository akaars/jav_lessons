package il.stqa.jav.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    gotoGroupLink();
    addNewGroup();
    fillGroupForm(new GroupForm("test1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
