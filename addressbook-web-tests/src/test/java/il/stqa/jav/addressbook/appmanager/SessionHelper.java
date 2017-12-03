package il.stqa.jav.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver; /**
 * Created by ilya on 12/3/17
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(FirefoxDriver wd) {
    super(wd);
  }
  
  public void login(String username, String password) {
    sendText(By.name("user"), username);
    sendText(By.name("pass"), password);
   clickElement(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
