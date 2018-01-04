/**
 * Created by ilya on 12/3/17
 */
package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.GroupForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupLink();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupForm().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupForm> before=app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupForm>  after=app.group().list();
    Assert.assertEquals(after.size(), before.size()-1);
    before.remove(before.size() - 1);
    for (int i=0; i < after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }

}
