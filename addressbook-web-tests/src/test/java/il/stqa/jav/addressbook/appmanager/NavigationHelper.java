package il.stqa.jav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by ilya on 12/3/17
 */
public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupLink() {
    wd.findElement(By.linkText("groups")).click();
  }
}
