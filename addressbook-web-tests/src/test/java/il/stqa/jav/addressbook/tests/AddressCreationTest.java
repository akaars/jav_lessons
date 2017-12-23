package il.stqa.jav.addressbook.tests;


import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.annotations.Test;

public class AddressCreationTest extends TestBase{

  @Test
  public void testNewAddressCreation() {
    app.getAddrHelper().gotoNewAddrLink();
    app.getAddrHelper().fillAddrForm(new AddressForm("John", "D", "Dow", null,
            "Mr", "test1",null, "+44-2-12-85-06", "test@mail.ru"),
            true);
    app.getNavigationHelper().submit();
    app.getNavigationHelper().backToHome();
  }





}
