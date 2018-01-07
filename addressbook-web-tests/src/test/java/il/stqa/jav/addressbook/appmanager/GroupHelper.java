package il.stqa.jav.addressbook.appmanager;
import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void selectByIndex(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void edit() {
    clickElement(By.name("edit"));
  }

  public void update() { clickElement(By.cssSelector("input[name='update']")); }

  public void create(GroupForm group) {
    addNewGroup();
    fillForm(group);
    new NavigationHelper(wd).submit();
    gotoGroupPage();
  }

  public void updateGroup(GroupForm group) {
    selectById(group.getGroupId());
    edit();
    fillForm(group);
    update();
    gotoGroupPage();
  }

  public void delete(GroupForm group) {
    selectById(group.getGroupId());
    delete();
    gotoGroupPage();
  }

  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "'")).click();
  }


  public Groups all() {
    Groups groups = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements){
      String name = element.getText();
      int groupId = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupForm().withId(groupId).withName(name));
    }
    return groups;
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
