package il.stqa.jav.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressCreationTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupLink();
      app.group().create(new GroupForm().withName("test_group"));
    }
  }

  @Test(enabled=true, dataProvider = "validGroupsJson")
  public void testNewAddressCreation(AddressForm addr) {
    Groups groups = app.db().groups();
    Addrs before = app.db().addrs();
    File photo = new File("src/test/resources/avatar.jpg");
    addr.withPhoto(photo).inGroup(groups.iterator().next());
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
