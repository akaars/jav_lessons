package il.stqa.jav.addressbook.model;

import java.util.Objects;

public class AddressForm {

  private final String firstName;
  private final String midName;
  private final String secondName;
  private final String nickName;
  private final String title;
  private final String group;
  private final String physicalAddr;
  private final String homePhone;
  private final String eMail;


  public AddressForm(String firstName, String midName, String secondName, String nickName, String title,
                     String group, String physicalAddr, String homePhone, String eMail) {
    this.firstName = firstName;
    this.midName = midName;
    this.secondName = secondName;
    this.nickName = nickName;
    this.title = title;
    this.group = group;
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

  public String getGroup() { return group; }

  public String getPhysicalAddr() {
    return physicalAddr;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String geteMail() {
    return eMail;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddressForm that = (AddressForm) o;
    return Objects.equals(firstName, that.firstName) &&
//            Objects.equals(midName, that.midName) &&
              Objects.equals(secondName, that.secondName);
//            Objects.equals(nickName, that.nickName) &&
//            Objects.equals(title, that.title) &&
//            Objects.equals(group, that.group) &&
//            Objects.equals(physicalAddr, that.physicalAddr) &&
//            Objects.equals(homePhone, that.homePhone) &&
//            Objects.equals(eMail, that.eMail);
  }

  @Override
  public int hashCode() {

//    return Objects.hash(firstName, midName, secondName, nickName, title, group, physicalAddr, homePhone, eMail);
    return Objects.hash(firstName, secondName);
  }

}
