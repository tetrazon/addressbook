package com.smuniov.addressbook.service;

import com.smuniov.addressbook.entity.Address;

import java.util.Set;

public interface AddressService {
    Set<Address> getUpdatedAddresses(Set<Address> addressesToCheck, Set<Address> oldAddresses);
    void deleteNonUsedAddresses(Set<Address> oldAddresses);
}
