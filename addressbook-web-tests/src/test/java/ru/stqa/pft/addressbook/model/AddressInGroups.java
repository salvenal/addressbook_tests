package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AddressInGroups extends ForwardingSet<AddressInGroupData> {

  private Set<AddressInGroupData> delegate;

  public AddressInGroups(AddressInGroups addressInGroups) {
    this.delegate = new HashSet<AddressInGroupData>(addressInGroups.delegate);
  }

  public AddressInGroups() {
    this.delegate = new HashSet<AddressInGroupData>();
  }

  public AddressInGroups(Collection<AddressInGroupData> addressInGroups) {
    this.delegate = new HashSet<AddressInGroupData>(addressInGroups);
  }


  @Override
  protected Set<AddressInGroupData> delegate() {
    return delegate;
  }

  public AddressInGroups withAdded(AddressInGroupData addressInGroup) {
    AddressInGroups addressInGroups = new AddressInGroups(this);
    addressInGroups.add(addressInGroup);
    return addressInGroups;
  }

  public AddressInGroups without(AddressInGroupData addressInGroup) {
    AddressInGroups addressInGroups = new AddressInGroups(this);
    addressInGroups.remove(addressInGroup);
    return addressInGroups;
  }

}


