package il.stqa.jav.addressbook.model;

import java.util.Objects;

public class GroupForm {
  private int groupId = Integer.MAX_VALUE;
  private String groupName;
  private String header;
  private String footer;

  public GroupForm withId(int groupId) {
    this.groupId = groupId;
    return this;
  }

  public GroupForm withName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public GroupForm withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupForm withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public int getGroupId() { return groupId; }

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
    return groupId == groupForm.groupId &&
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
