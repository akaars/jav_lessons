package il.stqa.jav.addressbook.tests;


import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class AddressCreationTest extends TestBase{

  @Test
  public void testNewAddressCreation() {
    List<AddressForm> before = app.addr().list();
    AddressForm newAddrGroup =
            new AddressForm().withFirstName("John").withSecondName("Dow").withHomePhone("+44-2-12-85-06").withEmail("test@mail.ru");
    app.addr().add(newAddrGroup);
    List<AddressForm> after = app.addr().list();
    before.add(newAddrGroup);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
