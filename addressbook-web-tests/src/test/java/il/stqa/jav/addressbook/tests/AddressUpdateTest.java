package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressUpdateTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().addrs().size() == 0){
      app.addr().add(new AddressForm().withFirstName("John").withSecondName("Dow")
              .withPhoto(new File("src/test/resources/avatar.jpg")));
    }
  }

  @Test
  public void testAddressUpdate() {
    Addrs before =  app.db().addrs();
    AddressForm addrToUpdate = before.iterator().next();
    AddressForm addrForm = new
            AddressForm().withFirstName("Mike").withSecondName("Mickelsson").withId(addrToUpdate.getId());
    app.addr().updateAddress(addrToUpdate, addrForm);
    app.goTo().home();
    Addrs after =  app.db().addrs();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(addrToUpdate).withAdded(addrForm)));
    verifyAddressListUI();
  }

}
