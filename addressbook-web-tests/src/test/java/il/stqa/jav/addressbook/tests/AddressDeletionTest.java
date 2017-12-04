package il.stqa.jav.addressbook.tests;

import org.testng.annotations.Test;

public class AddressDeletionTest extends TestBase {
  @Test
  public void testAddressDeletion() {
    app.getNavigationHelper().selectFirstAvailableCheckbox();
    app.getAddrHelper().deleteAddressEntry();
    app.getNavigationHelper().acceptAlert();
  }
}
