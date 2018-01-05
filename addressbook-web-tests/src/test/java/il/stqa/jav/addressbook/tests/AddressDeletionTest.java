package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.addr().all().size() == 0){
      app.addr().add(new AddressForm().withFirstName("John").withSecondName("Dow"));
    }
  }

  @Test
  public void testAddressDeletion() {
    Addrs before = app.addr().all();
    AddressForm addrToDelete = before.iterator().next();
    app.addr().delete(addrToDelete);
    app.goTo().home();
    Addrs after = app.addr().all();
    assertThat(after.size(), equalTo(before.size()-1));
    assertThat(after, equalTo(before.without(addrToDelete)));
  }
}
