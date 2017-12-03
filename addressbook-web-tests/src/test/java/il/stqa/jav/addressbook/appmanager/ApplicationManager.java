package il.stqa.jav.addressbook.appmanager;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.TimeUnit;

/**
 * Created by ilya on 12/3/17
 */
public class ApplicationManager {
  FirefoxDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private AddrHelper addrHelper;


  public void init() {
    String ffLocation = new String();
    if (SystemUtils.IS_OS_LINUX) {
      ffLocation = "/home/ilya/firefoxESR/firefox/firefox";
    } else if (SystemUtils.IS_OS_MAC) {
      ffLocation = "/Applications/FirefoxESR.app/Contents/MacOS/firefox-bin";
    }
    wd = new FirefoxDriver(new
            FirefoxOptions().setLegacy(true).setBinary(ffLocation));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    addrHelper = new AddrHelper(wd);
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://192.168.56.101/addressbook/index.php");
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public AddrHelper getAddrHelper() {
    return addrHelper;
  }
}
