package il.stqa.jav.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl")+ "signup_page.php");
    sendText(By.name("username"), username);
    sendText(By.name("email"), email);
    clickElement(By.cssSelector("input[value='Signup'"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    sendText(By.name("password"), password);
    sendText(By.name("password_confirm"), password);
    clickElement(By.cssSelector("button[type='submit'"));
  }
}
