package il.stqa.jav.addressbook.appmanager;
import il.stqa.jav.addressbook.model.GroupForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 12/3/17
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    clickElement(By.linkText("group page"));
  }

  public void fillForm(GroupForm groupForm) {
    sendText(By.name("group_name"), groupForm.getGroupName());
    sendText(By.name("group_header"), groupForm.getHeader());
    sendText(By.name("group_footer"), groupForm.getFooter());
  }

  public void addNewGroup() {
    clickElement(By.name("new"));
  }

  public void delete() {
    clickElement(By.xpath("//div[@id='content']/form/input[5]"));
  }

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void update() {
    clickElement(By.name("edit"));
  }

  public void create(GroupForm group) {
    addNewGroup();
    fillForm(group);
    new NavigationHelper(wd).submit();
    gotoGroupPage();
  }


  public void delete(int index) {
    select(index);
    delete();
    gotoGroupPage();
  }

  public List<GroupForm> list() {
    List<GroupForm> groups = new ArrayList<GroupForm>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements){
      String name = element.getText();
      int groupId = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupForm().withId(groupId).withName(name));
    }
    return groups;
  }
}
