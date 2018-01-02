package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddressDeletionTest extends TestBase {
  @Test
  public void testAddressDeletion() {
    if (!app.getAddrHelper().isAddrEntryExists()){
      app.getAddrHelper().createNewAddrEntry(new AddressForm("John", null, "Dow",
              null, null, null, null, null, null));
    }
    List<AddressForm> before = app.getAddrHelper().getAddrList();
    app.getNavigationHelper().selectFirstAvailableCheckbox();
    app.getAddrHelper().deleteAddressEntry();
    app.getNavigationHelper().acceptAlert();
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    app.getNavigationHelper().home();
    List<AddressForm> after = app.getAddrHelper().getAddrList();
    before.remove(0);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
