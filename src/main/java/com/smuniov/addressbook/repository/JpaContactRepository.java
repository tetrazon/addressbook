package com.smuniov.addressbook.repository;

import com.smuniov.addressbook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaContactRepository extends JpaRepository<Contact, Integer> {
    void deleteAllByPersonId(int id);
}
