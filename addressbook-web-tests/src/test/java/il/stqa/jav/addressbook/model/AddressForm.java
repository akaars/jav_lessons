package il.stqa.jav.addressbook.model;

public class AddressForm {
  private final String firstName;
  private final String midName;
  private final String secondName;
  private final String nickName;
  private final String title;
  private final String physicalAddr;
  private final String homePhone;
  private final String eMail;

  public AddressForm(String firstName, String midName, String secondName, String nickName, String title, String physicalAddr, String homePhone, String eMail) {
    this.firstName = firstName;
    this.midName = midName;
    this.secondName = secondName;
    this.nickName = nickName;
    this.title = title;
    this.physicalAddr = physicalAddr;
    this.homePhone = homePhone;
    this.eMail = eMail;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMidName() {
    return midName;
  }

  public String getSecondName() {
    return secondName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getTitle() {
    return title;
  }

  public String getPhysicalAddr() {
    return physicalAddr;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String geteMail() {
    return eMail;
  }
}
