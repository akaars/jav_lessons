package il.stqa.jav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {
  protected FirefoxDriver wd;

  public HelperBase(FirefoxDriver wd) {
    this.wd = wd;
  }

  protected void clickElement(By locator) {
    wd.findElement(locator).click();
  }

  protected void selectElement(By locator) {
    wd.findElement(locator).isSelected();
  }

  protected void sendText(By locator, String text) {
    clickElement(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
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
