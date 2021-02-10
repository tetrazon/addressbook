package com.smuniov.addressbook.service;

import com.smuniov.addressbook.entity.Contact;
import com.smuniov.addressbook.entity.Person;

import java.util.List;

public interface ContactService {
    List<Contact> getContactsWithPerson(Person person, List<Contact> contacts);
    void clearContacts(Person person);
}
