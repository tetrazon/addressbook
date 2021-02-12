package com.smuniov.addressbook.service;

import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.entity.Contact;
import com.smuniov.addressbook.entity.Person;

import java.util.List;

public interface PersonService {
    List<PersonDto> getAll();
    PersonDto getById(String id);
    List<PersonDto> getByName(String name);
    PersonDto update(PersonDto personDto);
    void deleteById(int id);
    PersonDto create(PersonDto personDto);
}
