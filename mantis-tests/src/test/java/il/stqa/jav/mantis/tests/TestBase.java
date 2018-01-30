package il.stqa.jav.mantis.tests;

import il.stqa.jav.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by ilya on 12/3/17
 */
public class TestBase {


  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",
          BrowserType.CHROME));


  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if(app.soap().isIssueFixed(issueId)){
      return false;
    }
    return true;
  }

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "mantisbt-2.10.0/config/config_inc.php", "mantisbt-2.10.0/config/config_inc.php.bak");
  }

  @AfterSuite
  public void tearDown() throws IOException {
    app.ftp().restore("mantisbt-2.10.0/config/config_inc.php.bak", "mantisbt-2.10.0/config/config_inc.php");
    app.stop();
  }


}
