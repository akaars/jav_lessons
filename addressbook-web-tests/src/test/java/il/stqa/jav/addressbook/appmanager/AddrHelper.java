package il.stqa.jav.addressbook.appmanager;

import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import il.stqa.jav.addressbook.model.GroupForm;
import il.stqa.jav.addressbook.model.Groups;
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


  public Addrs all() {
    Addrs addrs = new Addrs();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element: elements){
      String secondName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      int addrId = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      addrs.add(new AddressForm().withId(addrId).withFirstName(firstName).withSecondName(secondName));
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

  public void submitUpdate() { clickElement(By.cssSelector("input[name='update']")); }

  public void delete(AddressForm addr) {
    selectById(addr.getId());
    deleteAddressEntry();
    acceptAlert();
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void updateAddress(AddressForm addrEntry, AddressForm updatedAddrForm) {
    editEntry(addrEntry);
    fillAddrForm(updatedAddrForm);
    submitUpdate();
  }
  public void editEntry(AddressForm addr) {
    WebElement entry = wd.findElement(By.cssSelector("input[value='" + addr.getId() + "'"));
    entry.findElement(By.xpath("//td[8]/a")).click();
  }

  public void deleteAddressEntry() {
    clickElement(By.cssSelector("input[value='Delete']"));
  }

  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "'")).click();
  }

}
