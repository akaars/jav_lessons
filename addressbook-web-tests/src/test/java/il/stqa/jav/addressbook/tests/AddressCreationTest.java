package il.stqa.jav.addressbook.tests;


import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressCreationTest extends TestBase{

  @Test(enabled=true)
  public void testNewAddressCreation() {
    Addrs before = app.addr().all();
    File photo = new File("src/test/resources/avatar.jpg");
    AddressForm newAddrGroup =
            new AddressForm().withFirstName("John").withSecondName("Dow").withPhoto(photo);
    app.addr().add(newAddrGroup);
    Addrs after = app.addr().all();
    assertThat(before.size()+1, equalTo(after.size()));
    assertThat(after,
            equalTo(before.withAdded(newAddrGroup.withId(after.stream().mapToInt((a)->a.getId()).max().getAsInt()))));
  }

  @Test(enabled=false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/avatar.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
