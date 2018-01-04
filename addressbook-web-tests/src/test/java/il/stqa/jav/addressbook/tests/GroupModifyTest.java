package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModifyTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupLink();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupForm().withName("test1"));
    }
  }

  @Test
  public void testGroupUpdate(){
    List<GroupForm> before=app.group().list();
    int index = before.size() - 1;
    app.group().select(index);
    app.group().update();
    GroupForm group = new GroupForm().withId(before.get(index).getGroupId()).withName("test2").withHeader("test6").
            withFooter("test7");
    app.group().fillForm(group);
    app.goTo().update();
    app.group().gotoGroupPage();
    List<GroupForm>  after=app.group().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
