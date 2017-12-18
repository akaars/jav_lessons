package il.stqa.jav.addressbook.appmanager;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

/**
 * Created by ilya on 12/3/17
 */
public class ApplicationManager {
  WebDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private AddrHelper addrHelper;
  private String browser;
  private boolean isHeadless;

  public ApplicationManager(String browser, boolean isHeadless) {
    this.browser = browser;
    this.isHeadless = isHeadless;
  }


  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      String ffLocation = new String();
      if (SystemUtils.IS_OS_LINUX) {
        ffLocation = "/home/ilya/firefoxESR/firefox/firefox";
      } else if (SystemUtils.IS_OS_MAC) {
        ffLocation = "/Applications/FirefoxESR.app/Contents/MacOS/firefox-bin";
      }
      FirefoxOptions options = new FirefoxOptions();
      options.addArguments("window-size=1200x600");
      options.setLegacy(true);
      options.setBinary(ffLocation);
      if (isHeadless) {
        options.setHeadless(true);
      }
      wd = new FirefoxDriver(options);
    } else if (browser.equals(BrowserType.CHROME)) {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("window-size=1200x600");
      if (isHeadless) {
        options.setHeadless(true);
      }
      wd = new ChromeDriver(options);
    } else {
      System.out.println("Unsupported browser");
      System.exit(1);
    }

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
