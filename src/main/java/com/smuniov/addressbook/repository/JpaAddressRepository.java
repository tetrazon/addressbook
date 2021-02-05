package com.smuniov.addressbook.repository;

import com.smuniov.addressbook.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByCityAndAndStreet(String city, String street);
}
