package il.stqa.jav.addressbook;


import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddressCreationTest {
  FirefoxDriver wd;

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
            FirefoxOptions().setLegacy(true).setBinary(ffLocation));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.get("http://192.168.56.101/addressbook/index.php");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.cssSelector("html")).click();
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  @Test
  public void testNewAddressCreation() {
    gotoNewAddrLink();
    fillAddrForm(new AddressForm("John", "D", "Dow", "jonny_d", "Mr",
            "Somewhere over the rainbow, Baker st. 221", "+44-2-12-85-06",
            "test@mail.ru"));
    submit();
    backToHome();
  }

  private void backToHome() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submit() {
    wd.findElement(By.name("submit")).click();
  }

  private void fillAddrForm(AddressForm addressForm) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(addressForm.getFirstName());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(addressForm.getMidName());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(addressForm.getSecondName());
    wd.findElement(By.name("nickname")).click();
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys(addressForm.getNickName());
    wd.findElement(By.name("title")).click();
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys(addressForm.getTitle());
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(addressForm.getPhysicalAddr());
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(addressForm.getHomePhone());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(addressForm.geteMail());
  }

  private void gotoNewAddrLink() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod
  public void tearDown() {
    wd.quit();
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
