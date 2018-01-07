package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactVerificationTest extends TestBase {

  @Test
  public void ContactVerificationTest(){
    AddressForm address = app.addr().all().iterator().next();
    AddressForm addrToCompare = app.addr().getAddrDetailsFromEdit(address);
    assertThat(address.getHomePhone(), equalTo(cleanEntry(addrToCompare.getHomePhone())));
    assertThat(address.getWork(), equalTo(cleanEntry(addrToCompare.getWork())));
    assertThat(address.getMobile(), equalTo(cleanEntry(addrToCompare.getMobile())));
  }

  public static String cleanEntry(String element){
    return element.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
