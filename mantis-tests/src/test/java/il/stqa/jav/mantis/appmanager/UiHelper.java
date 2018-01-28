package il.stqa.jav.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by ilya on 1/28/18
 */
public class UiHelper extends HelperBase{
  public UiHelper (ApplicationManager app) {super(app);}


  public void login(String username, String password) {
    wd.get(app.getProperty("web.loginPage"));
    sendText(By.cssSelector("input[id='username'"), username);
    clickElement(By.cssSelector("input[type='submit'"));
    sendText(By.cssSelector("input[id='password'"), password);
    clickElement(By.cssSelector("input[type='submit'"));
  }

  public void resetPassword(String username){
    clickElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[6]/a/span"));
    clickElement(By.linkText("Manage Users"));
    clickElement(By.linkText(username));
    clickElement(By.cssSelector("input[value='Reset Password'"));
    }

  public void gotoLink(String link) {wd.get(link);}
  }

