package il.stqa.jav.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by ilya on 1/28/18
 */
public class ChangeUserPasswordTest extends TestBase {
  @Test
  public void changeUserPassword(){
    app.uiHelper().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"));
    app.uiHelper().resetPassword("user");
  }
}
