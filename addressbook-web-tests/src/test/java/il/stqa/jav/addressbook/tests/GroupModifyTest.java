package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModifyTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupLink();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupForm().withName("test1"));
    }
  }

  @Test
  public void testGroupUpdate(){
    Groups before=app.group().all();
    GroupForm updateGroup = before.iterator().next();
    GroupForm group = new GroupForm().withId(updateGroup.getGroupId()).withName("test2").withHeader("test6").
            withFooter("test7");
    app.group().updateGroup(group);
    Groups after=app.group().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(before.without(updateGroup).withAdded(group), equalTo(after));
  }

}
