package il.stqa.jav.mantis.tests;

import il.stqa.jav.mantis.model.MailMessage;
import il.stqa.jav.mantis.model.MantisUser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by ilya on 1/28/18
 */
public class ChangeUserPasswordTest extends TestBase {
  @BeforeMethod
  //get list of all users from the DB. If no username containing 'user', create such user
  public void preCondition() throws IOException, MessagingException {
    List<MantisUser> users = app.db().users();
    MantisUser randomUser = app.db().randomUser(users, 90);
    if (randomUser == null){
      System.out.println("No user found");
      long now = System.currentTimeMillis();
      String user = String.format("user%s", now);
      String password = "password";
      String email = String.format("user%s@localhost", now);
      app.james().createUser(user, password);
      app.registration().start(user, email);
      List<MailMessage> mailMessages = app.james().waitForMail(2, 60000, user, password);
      String confirmationLink = app.registration().findLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      assertTrue(app.newSession().login(user, password));
    }
  }

  @Test
  public void changeUserPassword() throws IOException, MessagingException {
    List<MantisUser> users = app.db().users();
    MantisUser user = app.db().randomUser(users, 90);
    String username = user.getUsername();
    String password = user.getUsername() + "blablabla";
    String email = username + "@localhost";
    //Assure the selected user doesn't exist in James. If exists - delete it, re-create and empty it's inbox
    app.james().deleteUser(username);
    app.james().createUser(username, password);
    app.james().drainEmail(username, password);
    app.uiHelper().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPass"));
    app.uiHelper().resetPassword(user.getUsername());
    List<MailMessage> mailMessages = app.james().waitForMail(2, 60000, username, password);
    String resetLink = app.registration().findLink(mailMessages, email);
    System.out.println(resetLink);
    app.registration().finish(resetLink, password);
    assertTrue(app.newSession().login(username, password));
  }
}
