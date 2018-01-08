package il.stqa.jav.addressbook.appmanager;

import il.stqa.jav.addressbook.model.AddressForm;
import il.stqa.jav.addressbook.model.Addrs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
      String homeAddress = element.findElement(By.xpath(".//td[4]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      addrs.add(new AddressForm().withId(addrId).withFirstName(firstName).withSecondName(secondName)
      .withPhysicalAddr(homeAddress).withAllEmails(allEmails).withAllPhones(allPhones));
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
    editAddressEntry(addrEntry);
    fillAddrForm(updatedAddrForm);
    submitUpdate();
  }
  public void editAddressEntry(AddressForm addr) {
    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", addr.getId()))).click();
  }

  public void deleteAddressEntry() {
    clickElement(By.cssSelector("input[value='Delete']"));
  }

  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "'")).click();
  }


  public AddressForm getAddrDetailsFromEdit(AddressForm addr) {
    AddressForm address = new AddressForm();
    editAddressEntry(addr);
    address.withFirstName(wd.findElement(By.cssSelector("input[name='firstname']")).getAttribute("value"))
            .withSecondName(wd.findElement(By.cssSelector("input[name='lastname']")).getAttribute("value"))
            .withHomePhone(wd.findElement(By.cssSelector("input[name='home']")).getAttribute("value"))
            .withHomePhone2(wd.findElement(By.cssSelector("input[name='phone2']")).getAttribute("value"))
            .withMobile(wd.findElement(By.cssSelector("input[name='mobile']")).getAttribute("value"))
            .withWork(wd.findElement(By.cssSelector("input[name='work']")).getAttribute("value"))
            .withPhysicalAddr(wd.findElement(By.cssSelector("textarea[name='address']")).getAttribute("value"))
            .withPhysicalAddr2(wd.findElement(By.cssSelector("textarea[name='address2']")).getAttribute("value"))
            .withEmail(wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value"))
            .witheMail2(wd.findElement(By.cssSelector("input[name='email2']")).getAttribute("value"))
            .witheteMail3(wd.findElement(By.cssSelector("input[name='email3']")).getAttribute("value"))
            .withId(addr.getId());
    return address;
  }
}
