package il.stqa.jav.addressbook;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by ilya on 12/3/17
 */
public class TestBase {
  FirefoxDriver wd;

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  @BeforeMethod
  public void setUp() throws Exception {
    String ffLocation = new String();
    if (SystemUtils.IS_OS_LINUX) {
      ffLocation = "/home/ilya/firefoxESR/firefox/firefox";
    }
    else if (SystemUtils.IS_OS_MAC) {
      ffLocation = "/Applications/FirefoxESR.app/Contents/MacOS/firefox-bin";
    }
    wd = new FirefoxDriver(new
            FirefoxOptions().setLegacy(true).setBinary(ffLocation));    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://192.168.56.101/addressbook/index.php");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  protected void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  protected void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  protected void fillGroupForm(GroupForm groupForm) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(groupForm.getGroupName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(groupForm.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(groupForm.getFooter());
  }

  protected void addNewGroup() {
    wd.findElement(By.name("new")).click();
  }

  protected void gotoGroupLink() {
    wd.findElement(By.linkText("groups")).click();
  }

  protected void deleteSelectedGroup() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
  }

  protected void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }

  @AfterMethod
  public void tearDown() {
    wd.quit();
  }
}
