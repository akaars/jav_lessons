package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class AddressUpdateTest extends TestBase{
  @Test
  public void testAddressUpdate() {
    if (! app.getAddrHelper().isAddrEntryExists()) {
      app.getAddrHelper().createNewAddrEntry(new AddressForm("Mike1", null, null,
              null, null, "test1", null, null, null));
    }
    List<AddressForm> before =  app.getAddrHelper().getAddrList();
    app.getAddrHelper().updateAddressEntry();
    AddressForm addrForm = new AddressForm("Mike", "N", "Mickelsson",
            "mickey_m", "Mr", "test1","Somewhere over the rainbow, Baker st. 221",
            "+44-2-12-85-06","test@mail.ru");
    app.getAddrHelper().fillAddrForm(addrForm);
    app.getNavigationHelper().update();
    app.getNavigationHelper().home();
    List<AddressForm> after =  app.getAddrHelper().getAddrList();
    before.remove(0);
    before.add(addrForm);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
