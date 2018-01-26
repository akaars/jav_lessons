package il.stqa.jav.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class HelperBase {
  protected WebDriver wd;
  protected ApplicationManager app;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
  }

  protected void clickElement(By locator) {
    wd.findElement(locator).click();
  }


  protected void sendText(By locator, String text) {
    clickElement(locator);
    if (text!=null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (! existingText.equals(text)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file){
    if (file != null){
      wd.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }
  public void selectFirstAvailableCheckbox() { clickElement(By.name("selected[]")); }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementExists(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException e){
      return false;
    }
  }
}
