package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.annotations.Test;

public class AddressUpdateTest extends TestBase{
  @Test
  public void testAddressUpdate() {
    app.getAddrHelper().updateAddressEntry();
    app.getAddrHelper().fillAddrForm(new AddressForm("Mike", "N", "Mickelsson",
            "mickey_m", "Mr", null,"Somewhere over the rainbow, Baker st. 221",
            "+44-2-12-85-06","test@mail.ru"), false);
    app.getNavigationHelper().update();
    app.getNavigationHelper().home();
  }
}
