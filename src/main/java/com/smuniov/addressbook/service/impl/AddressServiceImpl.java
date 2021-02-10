package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.entity.Address;
import com.smuniov.addressbook.repository.JpaAddressRepository;
import com.smuniov.addressbook.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Set<Address> getUpdatedAddresses(Set<Address> addressesToCheck, Set<Address> oldAddresses) {

        Set<Address> addressesToSave = new HashSet<>();
        Optional<Address> optionalAddress;
        for (Address address : addressesToCheck) {
            optionalAddress = addressRepository
                    .findByCityAndAndStreet(address.getCity(), address.getStreet());
            if (optionalAddress.isPresent()) {
                addressesToSave.add(optionalAddress.get());
            } else {
                addressesToSave.add(address);
            }
            if (oldAddresses != null && oldAddresses.contains(address)){
                oldAddresses.remove(address);
            }
        }

        deleteNonUsedAddresses(oldAddresses);
        return addressesToSave;
    }

    @Transactional
    public void deleteNonUsedAddresses(Set<Address> oldAddresses) {
        if (oldAddresses == null || oldAddresses.size() == 0){
            return;
        }
        for(Address address: oldAddresses){
            if (address.getPersons().size() == 1){
                addressRepository.delete(address);
            }
        }
        oldAddresses.clear();
    }

}
