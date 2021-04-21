package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.entity.Contact;
import com.smuniov.addressbook.entity.Person;
import com.smuniov.addressbook.repository.JpaContactRepository;
import com.smuniov.addressbook.service.ContactService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private final JpaContactRepository contactRepository;

    public ContactServiceImpl(JpaContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    @Transactional
    public List<Contact> getContactsWithPerson(Person person, List<Contact> contacts) {
        if (contacts == null){
            return Collections.emptyList();
        }
        for (Contact contact: contacts) {
            contact.setPerson(person);
        }
        return contacts;
    }

    @Override
    @Transactional
    public void clearContacts(Person person) {
        List<Contact> contacts = person.getContacts();
        if (contacts != null){
            List<Contact> contactsToClear = new ArrayList<>(contacts);
            if (!contactsToClear.isEmpty()) {
                person.getContacts().clear();
                contactRepository.deleteAll(contactsToClear);
            }
        }
    }

}
