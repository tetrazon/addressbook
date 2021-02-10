package com.smuniov.addressbook.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smuniov.addressbook.AddressbookApplication;
import com.smuniov.addressbook.dto.ContactDto;
import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.entity.Person;
import com.smuniov.addressbook.repository.JpaPersonRepository;
import com.smuniov.addressbook.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressbookApplication.class)
class PersonServiceImplTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private JpaPersonRepository personRepository;

    @Test
    void getAll() {
        List<PersonDto> personDtos = personService.getAll();
        assertTrue(personDtos.size() != 0);
    }

    @Test
    void getById() {
        assertTrue(personService.getById("1") != null);
    }

    @Test
    void getByName() {
        String name = "Victor";
        PersonDto person = personService.getByName(name);
        assertEquals(person.getName(), name);
    }

    @Test
    void deleteById() {
        Person person = new Person();
        person.setName("Test");
        int idToDelete = personRepository.save(person).getId();
        personService.deleteById(idToDelete);
        assertFalse(personRepository.existsById(idToDelete));
    }

    @Test
    void testCreateUpdateDelete() {
        ObjectMapper mapper = new ObjectMapper();
        String path = "src/test/resources/personDtoToCreate.json";
        PersonDto personDto = null;
        personDto = getPersonDto(mapper, path);
        PersonDto createdPersonDto = personService.create(personDto);
        personDto.setId(createdPersonDto.getId());
        ContactDto contactDto = new ContactDto();
        contactDto.setTelephone("+000000000000");
        List<ContactDto> newContacts = Collections.singletonList(contactDto);
        personDto.setContacts(newContacts);
        PersonDto updatedPersonDto = personService.update(personDto);
        assertEquals(personDto.getContacts().get(0).getTelephone(), updatedPersonDto.getContacts().get(0).getTelephone());
        personService.deleteById(personDto.getId());

    }

    @Test
    void testCreateDelete() {
        ObjectMapper mapper = new ObjectMapper();
        String path = "src/test/resources/personDtoToCreate.json";
        PersonDto personDto = null;
        personDto = getPersonDto(mapper, path);
        PersonDto createdPersonDto = personService.create(personDto);
        assert personDto != null;
        assertEquals(personDto.getName(), createdPersonDto.getName());
        assertEquals(personDto.getContacts().get(0).getTelephone(), createdPersonDto.getContacts().get(0).getTelephone());
        personService.deleteById(createdPersonDto.getId());
    }

    private PersonDto getPersonDto(ObjectMapper mapper, String path) {
        PersonDto personDto = null;
        try {
            personDto = mapper.readValue(new File(path), PersonDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personDto;
    }

}