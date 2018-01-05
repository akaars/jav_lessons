/**
 * Created by ilya on 12/3/17
 */
package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
    Set<GroupForm> before=app.group().all();
    GroupForm deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<GroupForm>  after=app.group().all();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
  }

}
