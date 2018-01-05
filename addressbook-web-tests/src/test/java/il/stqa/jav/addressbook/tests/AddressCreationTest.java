package il.stqa.jav.addressbook.tests;


import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressCreationTest extends TestBase{

  @Test
  public void testNewAddressCreation() {
    Addrs before = app.addr().all();
    AddressForm newAddrGroup =
            new AddressForm().withFirstName("John").withSecondName("Dow");
    app.addr().add(newAddrGroup);
    Addrs after = app.addr().all();
    assertThat(before.size()+1, equalTo(after.size()));
    assertThat(after,
            equalTo(before.withAdded(newAddrGroup.withId(after.stream().mapToInt((a)->a.getId()).max().getAsInt()))));
  }
}
