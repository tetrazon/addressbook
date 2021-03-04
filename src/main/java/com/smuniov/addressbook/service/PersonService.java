package com.smuniov.addressbook.service;

import com.smuniov.addressbook.dto.PersonDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> getAll();
    PersonDto getById(String id);
    PersonDto getByEmail(String email);
    PersonDto update(PersonDto personDto, boolean isUpdate);
    void deleteById(int id);
    PersonDto create(PersonDto personDto);
}
