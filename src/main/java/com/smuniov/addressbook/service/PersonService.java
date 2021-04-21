package com.smuniov.addressbook.service;

import com.smuniov.addressbook.dto.PersonDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface PersonService {
    List<PersonDto> getAll();
    ResponseEntity<Map<String, Object>> getAll(int page, int size);
    PersonDto getById(String id);
    PersonDto getByEmail(String email);
    ResponseEntity<PersonDto> update(PersonDto personDto,HttpServletRequest request, boolean isUpdate);
    void deleteById(int id);
    ResponseEntity<PersonDto> create(PersonDto personDto, HttpServletRequest request);
}
