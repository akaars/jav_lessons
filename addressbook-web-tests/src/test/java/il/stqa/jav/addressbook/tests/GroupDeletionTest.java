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
    app.goTo().groupLink();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupForm().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before=app.group().all();
    GroupForm deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size()-1));
    Groups  after=app.group().all();
    Assert.assertEquals(before.without(deletedGroup), after);
  }

}
