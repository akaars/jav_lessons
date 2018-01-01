package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by ilya on 12/3/17
 */
public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME, true);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}
