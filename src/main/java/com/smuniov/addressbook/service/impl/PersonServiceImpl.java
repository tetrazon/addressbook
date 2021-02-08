package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.entity.Address;
import com.smuniov.addressbook.entity.Contact;
import com.smuniov.addressbook.entity.Person;
import com.smuniov.addressbook.exceptions.BadDataException;
import com.smuniov.addressbook.mapper.PersonMapper;
import com.smuniov.addressbook.repository.JpaAddressRepository;
import com.smuniov.addressbook.repository.JpaPersonRepository;
import com.smuniov.addressbook.service.AddressService;
import com.smuniov.addressbook.service.ContactService;
import com.smuniov.addressbook.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {
    private final JpaPersonRepository personRepository;
    private final ContactService contactService;
    private final AddressService addressService;
    private final PersonMapper personMapper;

    public PersonServiceImpl(JpaPersonRepository personRepository, JpaAddressRepository addressRepository, ContactService contactService, AddressService addressService, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.contactService = contactService;
        this.addressService = addressService;
        this.personMapper = personMapper;
    }

    @Override
    @Transactional
    public List<PersonDto> getAll() {
        return personMapper.personsToPersonDtos(personRepository.findAll());
    }

    @Override
    @Transactional
    public PersonDto getById(String id) {
        int intId = -1;
        try {
            intId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new BadDataException("id is not a valid number");
        }
        return personMapper.personToPersonDto(personRepository.findById(intId).orElseThrow(() -> new BadDataException("id not found!")));
    }

    @Override
    @Transactional
    public PersonDto getByName(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return personMapper.personToPersonDto(personRepository.findByName(name).orElseThrow(() -> new BadDataException("wrong name!")));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (!optionalPerson.isPresent()) {
            throw new BadDataException("person with id " + id + " not found");
        }
        Person personToDelete = optionalPerson.get();
        personRepository.deleteAddressesReferringOnlyToThisPerson(personToDelete.getId());
        personRepository.delete(personToDelete);

    }

    @Override
    @Transactional
    public PersonDto update(PersonDto personDto) {
        return create(personDto);
    }

    @Override
    public PersonDto create(PersonDto personDto) {
        Person personFromDto = personMapper.personDtoToPerson(personDto);
        Person personToSave = new Person();
        Optional<Person> optionalPersonFromDb;
        Integer personId = personDto.getId();
        if (personId != null && personId >= 1) {
            optionalPersonFromDb = personRepository.findById(personId);
            personToSave = optionalPersonFromDb.orElseThrow(() -> new BadDataException("Wrong id!"));
        }
        Set<Address> addresses = addressService.setAddressesFromDbIfExists(personFromDto.getAddresses());
        addressService.setAddressesToPerson(personToSave, addresses);
        List<Contact> contactsToSave = contactService.getContacts(personFromDto, personToSave);
        contactService.deleteOldContactsAndSetNewContacts(personToSave, contactsToSave);
        personToSave.setName(personFromDto.getName());
        personRepository.save(personToSave);
        return personMapper.personToPersonDto(personToSave);
    }

}
