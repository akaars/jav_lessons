package il.stqa.jav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by ilya on 12/3/17
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupLink() {
    if (isElementExists(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Group")
            && isElementExists(By.name("new"))){
      return;
    }
    clickElement(By.linkText("groups"));
  }

  public void backToHome() {
    if (isElementExists(By.id("maintable"))) {
      return;
    }
    clickElement(By.linkText("home"));
  }

  public void submit() { clickElement(By.name("submit")); }

  public void update() { clickElement(By.name("update")); }

  public void home() { clickElement(By.linkText("home")); }

}
