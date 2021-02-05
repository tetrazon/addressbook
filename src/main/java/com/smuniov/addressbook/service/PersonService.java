package com.smuniov.addressbook.service;

import com.smuniov.addressbook.dto.PersonDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> getAll();
    PersonDto getById(String id);
    PersonDto getByName(String name);
    PersonDto update(PersonDto personDto);
    void deleteById(int id);
    PersonDto create(PersonDto personDto);
}
