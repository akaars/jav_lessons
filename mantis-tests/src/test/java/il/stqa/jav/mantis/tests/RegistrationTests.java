package il.stqa.jav.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {
  @Test
  public void Registration() {
    app.registration().start("user1", "user@localhost.localdomain");
  }
}
