package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.entity.Address;
import com.smuniov.addressbook.entity.Person;
import com.smuniov.addressbook.repository.JpaAddressRepository;
import com.smuniov.addressbook.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressServiceImpl implements AddressService {
    private final JpaAddressRepository addressRepository;

    public AddressServiceImpl(JpaAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Set<Address> setAddressesFromDbIfExists(Set<Address> addressesToCheck) {
        Set<Address> addressesToSave = new HashSet<>();
        Optional<Address> optionalAddress;
        for (Address address : addressesToCheck) {
            optionalAddress = addressRepository.findByCityAndAndStreet(address.getCity(), address.getStreet());
            if (optionalAddress.isPresent()) {
                addressesToSave.add(optionalAddress.get());
            } else {
                addressRepository.save(address);
                addressesToSave.add(address);
            }
        }
        addressesToCheck = addressesToSave;
        return addressesToSave;

    }

    @Override
    public void setAddressesToPerson(Person personToSave, Set<Address> addresses) {
        if (personToSave.getAddresses() != null) {
            personToSave.getAddresses().clear();
        }
        personToSave.setAddresses(addresses);
    }

}
