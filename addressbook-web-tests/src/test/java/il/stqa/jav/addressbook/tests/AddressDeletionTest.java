package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddressDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.addr().list().size() == 0){
      app.addr().add(new AddressForm().withFirstName("John").withSecondName("Dow"));
    }
  }

  @Test
  public void testAddressDeletion() {
    List<AddressForm> before = app.addr().list();
    app.goTo().selectFirstAvailableCheckbox();
    app.addr().delete();
    app.goTo().home();
    List<AddressForm> after = app.addr().list();
    before.remove(0);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
