package il.stqa.jav.addressbook.appmanager;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by ilya on 12/3/17
 */
public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private AddrHelper addrHelper;
  private String browser;
  private boolean isHeadless;
  private DbHelper dbHelper;

  public ApplicationManager(String browser, boolean isHeadless) {
    this.browser = browser;
    this.isHeadless = isHeadless;
    properties = new Properties();
  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
    if (browser.equals(BrowserType.FIREFOX)) {
      String ffLocation = new String();
      if (SystemUtils.IS_OS_LINUX) {
        ffLocation = properties.getProperty("ff.linuxLocation");
      } else if (SystemUtils.IS_OS_MAC) {
        ffLocation = properties.getProperty("ff.macLocation");
      }
      FirefoxOptions options = new FirefoxOptions();
      options.addArguments(String.format("window-size=%s", properties.getProperty("web.windowSize")));
      options.setLegacy(true);
      options.setBinary(ffLocation);
      if (isHeadless) {
        options.setHeadless(true);
      }
      if("".equals(properties.getProperty("selenium.server"))) {
        wd = new FirefoxDriver(options);
      }else {
        wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), options);
      }
    } else if (browser.equals(BrowserType.CHROME)) {
      ChromeOptions options = new ChromeOptions();
      options.addArguments(String.format("window-size=%s", properties.getProperty("web.windowSize")));
      if (isHeadless) {
        options.setHeadless(true);
      }
      if("".equals(properties.getProperty("selenium.server"))) {
        wd = new ChromeDriver(options);
      }else {
        wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), options);
      }
    } else {
      System.out.println("Unsupported browser");
      System.exit(1);
    }


    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    addrHelper = new AddrHelper(wd);
    wd.manage().timeouts().implicitlyWait(Integer.parseInt((properties.getProperty("wd.Timeout"))), TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPass"));
  }


  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public AddrHelper addr() {
    return addrHelper;
  }

  public DbHelper db() { return dbHelper; }
}
