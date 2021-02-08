package com.smuniov.addressbook.service;

import com.smuniov.addressbook.entity.Address;
import com.smuniov.addressbook.entity.Person;

import java.util.Set;

public interface AddressService {
    Set<Address> setAddressesFromDbIfExists(Set<Address> addressesToCheck);
    void setAddressesToPerson(Person personToSave, Set<Address> addresses);
}
