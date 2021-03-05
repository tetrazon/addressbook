package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.entity.Person;
import com.smuniov.addressbook.exceptions.BadDataException;
import com.smuniov.addressbook.mapper.PersonMapper;
import com.smuniov.addressbook.repository.JpaPersonRepository;
import com.smuniov.addressbook.service.AddressService;
import com.smuniov.addressbook.service.ContactService;
import com.smuniov.addressbook.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final JpaPersonRepository personRepository;
    private final ContactService contactService;
    private final AddressService addressService;
    private final PersonMapper personMapper;

    public PersonServiceImpl(JpaPersonRepository personRepository, ContactService contactService, AddressService addressService, PersonMapper personMapper) {
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
            LOGGER.error("id is not a valid number", e);
            throw new BadDataException("id is not a valid number");
        }
        return personMapper.personToPersonDto(personRepository.findById(intId).orElseThrow(() -> {
            LOGGER.error("id is not found!");
            return new BadDataException("id is not found!");
        }));
    }

    @Override
    @Transactional
    public PersonDto getByEmail(String email) {
        return personMapper.personToPersonDto(personRepository.findByEmail(email).orElseThrow(() -> {
            LOGGER.error("persons with such email is not found");
            return new BadDataException("wrong email!");
        }));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (!optionalPerson.isPresent()) {
            BadDataException badDataException = new BadDataException("person with id " + id + " not found");
            LOGGER.error("wrong person id ", badDataException);
            throw badDataException;
        }
        Person personToDelete = optionalPerson.get();
        addressService.deleteNonUsedAddresses(personToDelete.getAddresses());
        personRepository.delete(personToDelete);

    }

    @Override
    @Transactional
    public PersonDto create(PersonDto personDto) {
        if (!ObjectUtils.isEmpty(personDto.getId())) {
            LOGGER.error("id in person when creating: delete id from person first!");
            throw new BadDataException("delete id from person first!");
        }
        return update(personDto, false);
    }

    @Override
    @Transactional
    public PersonDto update(PersonDto personDto, boolean isUpdate) {
        Person personFromDto = personMapper.personDtoToPerson(personDto);
        Person personToSave = new Person();
        Optional<Person> optionalPersonFromDb;
        Integer personId = personDto.getId();
        if (isUpdate) {
            if (!ObjectUtils.isEmpty(personId) && personId >= 1) {
                optionalPersonFromDb = personRepository.findById(personId);
                personToSave = optionalPersonFromDb.orElseThrow(() -> {
                    LOGGER.error("wrong id in findById(personId) method");
                    return new BadDataException("Wrong id!");
                });
            } else {
                LOGGER.error("person without id when creating");
                throw new BadDataException("person to update must contain id");
            }
        }
        personToSave.setAddresses(addressService.getUpdatedAddresses(personFromDto.getAddresses(), personToSave.getAddresses()));
        contactService.clearContacts(personToSave);
        personToSave.setContacts(contactService.getContactsWithPerson(personToSave, personFromDto.getContacts()));
        personToSave.setName(personFromDto.getName());
        personToSave.setEmail(personFromDto.getEmail());
        personRepository.save(personToSave);
        return personMapper.personToPersonDto(personToSave);
    }

}
