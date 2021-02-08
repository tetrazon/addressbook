package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.entity.Contact;
import com.smuniov.addressbook.entity.Person;
import com.smuniov.addressbook.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Override
    public void deleteOldContactsAndSetNewContacts(Person personToSave, List<Contact> contactsToSave) {
        List<Contact> contactsToDelete = new ArrayList<>(personToSave.getContacts());
        for (Contact contactToDel: contactsToDelete){
            personToSave.removeContact(contactToDel);
        }
        for (Contact contactToAdd : contactsToSave){
            personToSave.addContact(contactToAdd);
        }
    }

    @Override
    public List<Contact> getContacts(Person personFromDto, Person personToSave) {
        List<Contact> contactsToSave = new ArrayList<>(personFromDto.getContacts());
        setPersonInContacts(personToSave, contactsToSave);
        return contactsToSave;
    }

    private void setPersonInContacts(Person person, List<Contact> contacts) {
        for (Contact contact : contacts) {
            person.addContact(contact);
        }
    }
}
