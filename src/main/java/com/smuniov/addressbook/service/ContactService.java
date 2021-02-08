package com.smuniov.addressbook.service;

import com.smuniov.addressbook.entity.Contact;
import com.smuniov.addressbook.entity.Person;

import java.util.List;

public interface ContactService {
    void deleteOldContactsAndSetNewContacts(Person personToSave, List<Contact> contactsToSave);
    List<Contact> getContacts(Person personFromDto, Person personToSave);
}
