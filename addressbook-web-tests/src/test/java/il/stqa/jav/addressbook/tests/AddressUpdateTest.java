package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.annotations.Test;

public class AddressUpdateTest extends TestBase{
  @Test
  public void testAddressUpdate() {
    if (! app.getAddrHelper().isAddrEntryExists()) {
      app.getAddrHelper().createNewAddrEntry(new AddressForm("Mike1", null,null,
              null,null,"test1",null,null,null));
    }
    app.getAddrHelper().updateAddressEntry();
    app.getAddrHelper().fillAddrForm(new AddressForm("Mike", "N", "Mickelsson",
            "mickey_m", "Mr", "test1","Somewhere over the rainbow, Baker st. 221",
            "+44-2-12-85-06","test@mail.ru"));
    app.getNavigationHelper().update();
    app.getNavigationHelper().home();
  }
}
