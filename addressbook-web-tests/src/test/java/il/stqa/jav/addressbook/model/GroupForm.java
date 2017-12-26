package il.stqa.jav.addressbook.model;

import java.util.Objects;

public class GroupForm {
  private final String groupId;
  private final String groupName;
  private final String header;
  private final String footer;


  public GroupForm(String groupName, String header, String footer) {
    this.groupId = null;
    this.groupName = groupName;
    this.header = header;
    this.footer = footer;
  }

  public GroupForm(String groupId, String groupName, String header, String footer) {
    this.groupId = groupId;
    this.groupName = groupName;
    this.header = header;
    this.footer = footer;
  }

  public String getGroupId() { return groupId; }

  public String getGroupName() {
    return groupName;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupForm groupForm = (GroupForm) o;
    return Objects.equals(groupId, groupForm.groupId) &&
            Objects.equals(groupName, groupForm.groupName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(groupId, groupName);
  }

  @Override
  public String toString() {
    return "GroupForm{" +
            "groupId='" + groupId + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
  }

}
