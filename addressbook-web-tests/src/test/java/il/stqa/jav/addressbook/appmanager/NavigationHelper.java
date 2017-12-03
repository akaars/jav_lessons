package il.stqa.jav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by ilya on 12/3/17
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupLink() {
    clickElement(By.linkText("groups"));
  }

  public void backToHome() {
    clickElement(By.linkText("home page"));
  }

  public void submit() { clickElement(By.name("submit")); }
}
