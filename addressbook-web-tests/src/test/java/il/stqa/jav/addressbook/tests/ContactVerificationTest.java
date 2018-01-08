package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactVerificationTest extends TestBase {

  @Test
  public void ContactVerificationTest(){
    AddressForm address = app.addr().all().iterator().next();
    AddressForm addrToCompare = app.addr().getAddrDetailsFromEdit(address);
    assertThat(address.getAllPhones(), equalTo(mergePhones(addrToCompare)));
    assertThat(address.getPhysicalAddr(), equalTo(addrToCompare.getPhysicalAddr()));
    assertThat(address.getAllEmails(), equalTo(mergeEmails(addrToCompare)));
  }

  private String mergeEmails(AddressForm contact) {
    return Arrays.asList(contact.geteMail(), contact.geteMail2(), contact.geteMail3()).
            stream().filter((s)->!s.isEmpty()).collect(Collectors.joining("\n"));
  }

  private String mergePhones(AddressForm contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWork(), contact.getHomePhone2())
            .stream().filter((s) -> !s.isEmpty()).map(ContactVerificationTest::cleanEntry)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanEntry(String element){
    return element.replaceAll("\\s", "").
            replaceAll("[-()]","");
  }
}
