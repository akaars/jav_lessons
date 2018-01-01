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

  public void returnToGroupPage() {
    clickElement(By.linkText("group page"));
  }

  public void fillGroupForm(GroupForm groupForm) {
    sendText(By.name("group_name"), groupForm.getGroupName());
    sendText(By.name("group_header"), groupForm.getHeader());
    sendText(By.name("group_footer"), groupForm.getFooter());
  }

  public void addNewGroup() {
    clickElement(By.name("new"));
  }

  public void deleteSelectedGroup() {
    clickElement(By.xpath("//div[@id='content']/form/input[5]"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initGroupUpdate() {
    clickElement(By.name("edit"));
  }

  public void createGroup(GroupForm group) {
    addNewGroup();
    fillGroupForm(group);
    new NavigationHelper(wd).submit();
    returnToGroupPage();
  }

  public boolean isGroupExists() {
    return isElementExists(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupForm> getGroupList() {
    List<GroupForm> groups = new ArrayList<GroupForm>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements){
      String name = element.getText();
      int groupId = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupForm group = new GroupForm(groupId, name, null,null);
      groups.add(group);

    }
    return groups;
  }
}
