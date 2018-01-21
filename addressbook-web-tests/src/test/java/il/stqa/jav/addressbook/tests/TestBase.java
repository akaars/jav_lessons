package il.stqa.jav.addressbook.tests;

import il.stqa.jav.addressbook.appmanager.ApplicationManager;
import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ilya on 12/3/17
 */
public class TestBase {
  Logger logger = LoggerFactory.getLogger(TestBase.class);


  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME),
          Boolean.parseBoolean(System.getProperty("headless", "false")));


  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod(alwaysRun = true)
  public void logTestStart(Method m, Object[] p){
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop test " + m.getName());
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUi")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalToObject(dbGroups.stream().map((g)->
              new GroupForm().withName(g.getGroupName()).withId(g.getGroupId())).collect(Collectors.toSet())));
    }
  }
}
