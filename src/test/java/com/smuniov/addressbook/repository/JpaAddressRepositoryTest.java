package com.smuniov.addressbook.repository;

import com.smuniov.addressbook.AddressbookApplication;
import com.smuniov.addressbook.entity.Address;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressbookApplication.class)
class JpaAddressRepositoryTest {
    @Autowired
    private JpaAddressRepository addressRepository;

    @Test
    public void testWriteReadDelete(){
        Address address = new Address();
        address.setCity("TestCity");
        address.setStreet("TestStreet");
        addressRepository.save(address);
        Optional<Address> optionalAddressFromDb = addressRepository.findByCityAndAndStreet(address.getCity(), address.getStreet());
        assertTrue(optionalAddressFromDb.isPresent());
        int addressId = optionalAddressFromDb.get().getId();
        assertEquals(optionalAddressFromDb.get().getCity(), address.getCity());
        assertEquals(optionalAddressFromDb.get().getStreet(), address.getStreet());
        addressRepository.deleteById(addressId);
        assertFalse(addressRepository.existsById(addressId));

    }
}