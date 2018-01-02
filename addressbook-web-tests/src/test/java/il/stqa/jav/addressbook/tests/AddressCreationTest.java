package il.stqa.jav.addressbook.tests;


import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class AddressCreationTest extends TestBase{

  @Test
  public void testNewAddressCreation() {
    List<AddressForm> before = app.getAddrHelper().getAddrList();
    AddressForm newAddrGroup = new AddressForm("John", null, "Dow", null,
            null, null,null, "+44-2-12-85-06", "test@mail.ru");
    app.getAddrHelper().createNewAddrEntry(newAddrGroup);
    List<AddressForm> after = app.getAddrHelper().getAddrList();
    before.add(newAddrGroup);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
