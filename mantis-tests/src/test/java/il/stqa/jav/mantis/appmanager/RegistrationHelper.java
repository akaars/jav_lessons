package il.stqa.jav.mantis.appmanager;

import il.stqa.jav.mantis.model.MailMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

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

  public String findLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m)->m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
