package il.stqa.jav.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver; /**
 * Created by ilya on 12/3/17
 */
public class SessionHelper {
  private FirefoxDriver wd;

  public SessionHelper(FirefoxDriver wd) {
    this.wd = wd;
  }
}
