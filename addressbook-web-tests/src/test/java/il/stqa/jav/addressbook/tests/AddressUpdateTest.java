package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressUpdateTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.addr().all().size() == 0){
      app.addr().add(new AddressForm().withFirstName("John").withSecondName("Dow"));
    }
  }

  @Test
  public void testAddressUpdate() {
    Addrs before =  app.addr().all();
    AddressForm addrToUpdate = before.iterator().next();
    AddressForm addrForm = new
            AddressForm().withFirstName("Mike").withSecondName("Mickelsson").withId(addrToUpdate.getId());
    app.addr().updateAddress(addrToUpdate, addrForm);
    app.goTo().home();
    Addrs after =  app.addr().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(addrToUpdate).withAdded(addrForm)));
  }

}
