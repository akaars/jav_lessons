package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class AddressUpdateTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.addr().list().size() == 0){
      app.addr().add(new AddressForm().withFirstName("John").withSecondName("Dow"));
    }
  }

  @Test
  public void testAddressUpdate() {
    List<AddressForm> before =  app.addr().list();
    app.addr().updateAddressEntry();
    AddressForm addrForm = new
            AddressForm().withFirstName("Mike").withMidName("N").withSecondName("Mickelsson").
            withNickName("mickey_m").withTitle("Mr").withPhysicalAddr("Somewhere over the rainbow, Baker st. 221").
            withHomePhone("+44-2-12-85-06").withEmail("test@mail.ru");
    app.addr().fillAddrForm(addrForm);
    app.goTo().update();
    app.goTo().home();
    List<AddressForm> after =  app.addr().list();
    before.remove(0);
    before.add(addrForm);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
