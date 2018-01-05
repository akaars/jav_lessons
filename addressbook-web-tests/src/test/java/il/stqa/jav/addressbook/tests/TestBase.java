package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * Created by ilya on 12/3/17
 */
public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX, false);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
