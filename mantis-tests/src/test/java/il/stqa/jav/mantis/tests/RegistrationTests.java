package il.stqa.jav.mantis.tests;

import il.stqa.jav.mantis.model.MailMessage;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {
//  @BeforeMethod
//  public void startMailServer(){
//    app.mail().start();
//  }
  @Test
  public void Registration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("%s@localhost", user);
    app.james().createUser(user, password);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(2, 60000, user, password);
    String confirmationLink = app.registration().findLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }




//  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
