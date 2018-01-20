package il.stqa.jav.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Addrs extends ForwardingSet<AddressForm> {

  private Set<AddressForm> delegate;

  public Addrs(Addrs addr) {
    this.delegate = new HashSet<AddressForm>(addr.delegate);
  }

  public Addrs() {
    this.delegate = new HashSet<AddressForm> ();
  }

  public Addrs(Collection<AddressForm> addrs) {
    this.delegate = new HashSet<AddressForm>(addrs);
  }

  @Override
  protected Set<AddressForm> delegate() {
    return delegate;
  }

  public Addrs withAdded(AddressForm addr) {
    Addrs addrs = new Addrs(this);
    addrs.add(addr);
    return addrs;
  }

  public Addrs without(AddressForm addr) {
    Addrs addrs = new Addrs(this);
    addrs.remove(addr);
    return addrs;
  }
}
