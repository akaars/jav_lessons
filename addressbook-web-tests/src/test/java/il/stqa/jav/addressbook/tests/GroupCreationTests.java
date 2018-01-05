package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.goTo().groupLink();
    Groups before=app.group().all();
    GroupForm group = new GroupForm().withName("test225");
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after,
            equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getGroupId()).max().getAsInt()))));
  }

}
