/**
 * Created by ilya on 12/3/17
 */
package il.stqa.jav.addressbook;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() {
    gotoGroupLink();
    selectGroup();
    deleteSelectedGroup();
    returnToGroupPage();
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
