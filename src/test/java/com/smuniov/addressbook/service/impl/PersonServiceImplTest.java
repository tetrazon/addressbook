package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.AddressbookApplication;
import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.entity.Person;
import com.smuniov.addressbook.repository.JpaPersonRepository;
import com.smuniov.addressbook.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressbookApplication.class)
@TestPropertySource("classpath:application.properties")
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
    void update() {
    }

    @Test
    void create() {
    }
}