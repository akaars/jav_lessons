package il.stqa.jav.mantis.appmanager;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by ilya on 12/3/17
 */
public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;
  private String browser;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

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

      wd = new FirefoxDriver(options);
    } else if (browser.equals(BrowserType.CHROME)) {
      ChromeOptions options = new ChromeOptions();
      options.addArguments(String.format("window-size=%s", properties.getProperty("web.windowSize")));
      wd = new ChromeDriver(options);
    } else {
      System.out.println("Unsupported browser");
      System.exit(1);
    }

    wd.manage().timeouts().implicitlyWait(Integer.parseInt((properties.getProperty("wd.Timeout"))), TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
  }


  public void stop() {
    wd.quit();
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public  String getProperty(String key) {
    return properties.getProperty(key);
  }
}