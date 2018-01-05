package il.stqa.jav.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupForm> {

  private Set<GroupForm> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GroupForm> (groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupForm> ();
  }

  @Override
  protected Set<GroupForm> delegate() {
    return delegate;
  }

  public Groups withAdded(GroupForm group) {
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups without(GroupForm group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }
}
