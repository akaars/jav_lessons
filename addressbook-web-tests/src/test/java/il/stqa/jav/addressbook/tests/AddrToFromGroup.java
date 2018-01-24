package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ilya on 1/24/18
 */
public class AddrToFromGroup extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().addrs().size() == 0){
      app.addr().add(new AddressForm().withFirstName("John").withSecondName("Dow")
              .withPhoto(new File("src/test/resources/avatar.jpg")));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupLink();
      app.group().create(new GroupForm().withName("contact_test"));
    }
  }

  @Test
  public void addRemoveContactToFromGroup() {
    Addrs listOfContacts = app.db().addrs();
    AddressForm addr = listOfContacts.iterator().next();
    Groups listOfGroups = app.db().groups();
    GroupForm group = listOfGroups.iterator().next();
    app.goTo().home();
    if (!addr.getGroups().isEmpty()) {
      if(addr.getGroups().contains(group)) {
        //If the the selected address already assigned to the group lets unassign it first
        app.addr().removeAddrFromGroup(addr, group);
        assertThat(addr.getGroups().without(group), CoreMatchers.equalTo(app.db().addrs().stream().
                filter((a)->a.getId() == addr.getId()).collect(Collectors.toList()).get(0).getGroups()));
        app.goTo().home();
      }
    }
    //Display all groups
    app.addr().selectDisplayGroup("[all]");
    app.addr().addAddrToGroup(addr, group);
    assertThat(addr.getGroups().withAdded(group), CoreMatchers.equalTo(app.db().addrs().stream().
            filter((a)->a.getId() == addr.getId()).collect(Collectors.toList()).get(0).getGroups()));
    app.goTo().home();
    app.addr().removeAddrFromGroup(addr, group);
    assertThat(addr.getGroups().without(group), CoreMatchers.equalTo(app.db().addrs().stream().
            filter((a)->a.getId() == addr.getId()).collect(Collectors.toList()).get(0).getGroups()));
  }



}
