package com.smuniov.addressbook.controller;

import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/address-book/persons")
@Log4j2
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPersons(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int size,
                                                             HttpServletRequest request){
        logRequest(request);
        return personService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public PersonDto getById(@PathVariable("id") String id, HttpServletRequest request){
        logRequest(request);
        return personService.getById(id);
    }

    @GetMapping("/person/")
    public PersonDto getByEmail(@RequestParam("email") String email, HttpServletRequest request){
        logRequest(request);
        return personService.getByEmail(email);
    }

    @DeleteMapping("/person/{id}")
    public void delete(@PathVariable("id") int id, HttpServletRequest request) {
        logRequest(request);
        personService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@Valid @RequestBody PersonDto personDto, HttpServletRequest request){
        logRequest(request);
        return personService.create(personDto, request);
    }

    @PutMapping
    public ResponseEntity<PersonDto> update(@Valid @RequestBody PersonDto personDto, HttpServletRequest request){
        logRequest(request);
        return personService.update(personDto, request, true);
    }

    private void logRequest(HttpServletRequest request) {
        log.info(request.getRequestURI() + ", -" +request.getMethod() + " is called");
    }

}
