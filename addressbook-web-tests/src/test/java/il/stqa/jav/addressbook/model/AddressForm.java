package il.stqa.jav.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class AddressForm {
  @Expose
  private String firstName;
  private String midName;
  @Expose
  private String secondName;
  private String nickName;
  private String title;
  private String group;
  private String physicalAddr;
  private String physicalAddr2;
  @Expose
  private String homePhone;
  private String homePhone2;
  @Expose
  private String mobile;
  @Expose
  private String work;
  @Expose
  private String eMail;
  @Expose
  private String eMail2;
  @Expose
  private String eMail3;
  private String allPhones;
  private String allEmails;
  private int id = Integer.MAX_VALUE;;
  private File photo;


  public AddressForm withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public AddressForm withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public AddressForm withMidName(String midName) {
    this.midName = midName;
    return this;
  }

  public AddressForm withSecondName(String secondName) {
    this.secondName = secondName;
    return this;
  }

  public AddressForm withNickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public AddressForm withTitle(String title) {
    this.title = title;
    return this;
  }

  public AddressForm withGroup(String group) {
    this.group = group;
    return this;
  }

  public AddressForm withPhysicalAddr(String physicalAddr) {
    this.physicalAddr = physicalAddr;
    return this;
  }

  public AddressForm withPhysicalAddr2(String physicalAddr2) {
    this.physicalAddr2 = physicalAddr2;
    return this;
  }

  public AddressForm withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public AddressForm withHomePhone2(String homePhone2) {
    this.homePhone2 = homePhone2;
    return this;
  }

  public AddressForm withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public AddressForm withWork(String work) {
    this.work = work;
    return this;
  }

  public AddressForm withEmail(String eMail) {
    this.eMail = eMail;
    return this;
  }


  public AddressForm witheMail2(String eMail2) {
    this.eMail2 = eMail2;
    return this;
  }


  public AddressForm witheMail3(String eMail3) {
    this.eMail3 = eMail3;
    return this;
  }


  public AddressForm withId(int id) {
    this.id = id;
    return this;
  }

  public AddressForm withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public AddressForm withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }


  public int getId() {
    return id;
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

  public String getPhysicalAddr2() { return physicalAddr2; }

  public String getHomePhone() {
    return homePhone;
  }

  public String getHomePhone2() { return homePhone2; }

  public String getWork() { return work; }

  public String getMobile() { return mobile; }

  public String geteMail() {
    return eMail;
  }

  public String geteMail2() { return eMail2; }

  public String geteMail3() { return eMail3; }

  public String getAllPhones() { return allPhones; }

  public String getAllEmails() { return allEmails; }

  public File getPhoto() { return photo; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddressForm that = (AddressForm) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(secondName, that.secondName) &&
            Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(firstName, secondName, id);
  }

  @Override
  public String toString() {
    return "AddressForm{" +
            "firstName='" + firstName + '\'' +
            ", secondName='" + secondName + '\'' +
            ", id='" + id + '\'' +
            '}';
  }



}
