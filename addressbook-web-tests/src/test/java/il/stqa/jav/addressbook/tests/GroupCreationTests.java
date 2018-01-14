package il.stqa.jav.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validGroupsCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new GroupForm().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupForm.class);
    List<GroupForm> groups = (List<GroupForm>) xstream.fromXML(xml);
    return groups.stream().map((g)-> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupForm> groups = gson.fromJson(json, new TypeToken<List<GroupForm>>(){}.getType());
    return groups.stream().map((g)-> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validGroupsJson")
  public void testGroupCreation(GroupForm testGroup) {
    app.goTo().groupLink();
    Groups before=app.group().all();
    app.group().create(testGroup);
    Groups after=app.group().all();
    assertThat(app.group().count(), equalTo(before.size()+1));
    assertThat(after,
            equalTo(before.withAdded(testGroup.withId(after.stream().mapToInt((g)->g.getGroupId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    app.goTo().groupLink();
    Groups before=app.group().all();
    GroupForm group = new GroupForm().withName("test225'");
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(app.group().count(), equalTo(before.size()));
    assertThat(after, equalTo(before));
  }
}
