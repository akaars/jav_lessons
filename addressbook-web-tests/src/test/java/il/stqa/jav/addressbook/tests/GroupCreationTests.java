package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {
    app.goTo().groupLink();
    List<GroupForm>  before=app.group().list();
    GroupForm group = new GroupForm().withName("test225");

    app.group().create(group);
    List<GroupForm>  after=app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId())).get().getGroupId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
