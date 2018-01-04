package il.stqa.jav.addressbook.appmanager;

import il.stqa.jav.addressbook.model.AddressForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddrHelper extends HelperBase {
  AddrHelper(WebDriver wd) { super(wd); }

  private void gotoNewAddrLink() {
    clickElement(By.linkText("add new"));
  }

  public void acceptAlert() { wd.switchTo().alert().accept(); }

  public List<AddressForm> list(){
    List<AddressForm> addrs = new ArrayList<AddressForm>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements){
      String secondName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      AddressForm addr = new AddressForm().withFirstName(firstName).withSecondName(secondName);
      addrs.add(addr);
    }
    return addrs;
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
    if (isElementExists(By.name("new_group"))) {
      new Select(wd.findElement(By.name("new_group"))).getFirstSelectedOption();
    }
  }

  public void add(AddressForm addrEntry) {
    NavigationHelper tmpNavHelper = new NavigationHelper(wd);
    gotoNewAddrLink();
    fillAddrForm(addrEntry);
    tmpNavHelper.submit();
    tmpNavHelper.home();

  }

  public void delete() {
    deleteAddressEntry();
    acceptAlert();
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void updateAddressEntry() {
    clickElement(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a"));
  }

  public void deleteAddressEntry() {
    clickElement(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
  }

  public boolean isAddrEntryExists() {
    return isElementExists(By.xpath("//table[@id='maintable']/tbody/tr/td"));
  }
}
