package il.stqa.jav.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressCreationTest extends TestBase{

  @Test(enabled=true, dataProvider = "validGroupsJson")
  public void testNewAddressCreation(AddressForm addr) {
    Addrs before = app.db().addrs();
    File photo = new File("src/test/resources/avatar.jpg");
    addr.withPhoto(photo);
    app.goTo().home();
    app.addr().add(addr);
    Addrs after = app.db().addrs();
    assertThat(before.size()+1, equalTo(after.size()));
    assertThat(after,
            equalTo(before.withAdded(addr.withId(after.stream().mapToInt((a)->a.getId()).max().getAsInt()))));
  }

  @Test(enabled=false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/avatar.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }


  @DataProvider
  public Iterator<Object[]> validGroupsJson() throws IOException {
    String json = "";
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/addrs.json")))) {
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
    }
    Gson gson = new Gson();
    List<AddressForm> addrs = gson.fromJson(json, new TypeToken<List<AddressForm>>(){}.getType());
    return addrs.stream().map((g)-> new Object[]{g}).collect(Collectors.toList()).iterator();
  }


}
