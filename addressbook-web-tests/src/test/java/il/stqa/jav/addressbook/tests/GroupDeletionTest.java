/**
 * Created by ilya on 12/3/17
 */
package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupLink();
      app.group().create(new GroupForm().withName("deletion test"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before=app.db().groups();
    GroupForm deletedGroup = before.iterator().next();
    app.goTo().groupLink();
    app.group().delete(deletedGroup);
    Groups  after=app.db().groups();
    assertThat(after.size(), equalTo(before.size()-1));
    Assert.assertEquals(before.without(deletedGroup), after);
  }

}
