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
  private WebDriver wd;
  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mail;
  private JamesHelper jamesHelper;
  private UiHelper uiHelper;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

  }


  public void stop() {
    if (wd != null) {
      wd.quit();
    }
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public  String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper =  new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public UiHelper uiHelper() {
    if (uiHelper == null) {
      uiHelper = new UiHelper(this);
    }
    return uiHelper;
  }

  public WebDriver getDriver() {
    if (wd == null) {
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
    return wd;
  }

  public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }
  public MailHelper mail() {
    if (mail == null) {
      mail = new MailHelper(this);
    }
    return mail;
  }

  public JamesHelper james() {
    if (jamesHelper == null) {
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }
}