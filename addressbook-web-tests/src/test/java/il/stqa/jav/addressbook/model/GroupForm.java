package il.stqa.jav.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name="group_list")
public class GroupForm {
  @XStreamOmitField
  @Id
  @Column(name="group_id")
  private int groupId = Integer.MAX_VALUE;
  @Expose
  @Column(name="group_name")
  private String groupName;
  @Expose
  @Column(name="group_header")
  @Type(type="text")
  private String header;
  @Expose
  @Column(name="group_footer")
  @Type(type="text")
  private String footer;

  public Addrs getAddrs() {
    return new Addrs(addrs);
  }

  @ManyToMany(mappedBy = "groups")
  private Set<AddressForm> addrs = new HashSet<AddressForm>();

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
            Objects.equals(groupName, groupForm.groupName) &&
            Objects.equals(header, groupForm.header) &&
            Objects.equals(footer, groupForm.footer);
  }

  @Override
  public int hashCode() {

    return Objects.hash(groupId, groupName, header, footer);
  }

  @Override
  public String toString() {
    return "GroupForm{" +
            "groupId='" + groupId + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
  }

}
