package il.stqa.jav.addressbook.appmanager;

import il.stqa.jav.addressbook.model.AddressForm;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddrHelper extends HelperBase {
  public AddrHelper(FirefoxDriver wd) { super(wd); }

  public void gotoNewAddrLink() {
    clickElement(By.linkText("add new"));
  }

  public void fillAddrForm(AddressForm addressForm) {
    sendText(By.name("firstname"), addressForm.getFirstName());
    sendText(By.name("middlename"), addressForm.getMidName());
    sendText(By.name("lastname"), addressForm.getSecondName());
    sendText(By.name("nickname"), addressForm.getNickName());
    sendText(By.name("title"), addressForm.getTitle());
    sendText(By.name("address"), addressForm.getPhysicalAddr());
    sendText(By.name("home"), addressForm.getHomePhone());
    sendText(By.name("email"), addressForm.geteMail());
  }
}
