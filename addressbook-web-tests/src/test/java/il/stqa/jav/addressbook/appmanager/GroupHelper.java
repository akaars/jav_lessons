package il.stqa.jav.addressbook.appmanager;
import il.stqa.jav.addressbook.model.GroupForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

  public void selectGroup() {
    clickElement(By.name("selected[]"));
  }

  public void initGroupUpdate() {
    clickElement(By.name("edit"));
  }

  public void createGroup(GroupForm group) {
    addNewGroup();
    fillGroupForm(new GroupForm("test1", null, null));
    new NavigationHelper(wd).submit();
    returnToGroupPage();
  }
}
