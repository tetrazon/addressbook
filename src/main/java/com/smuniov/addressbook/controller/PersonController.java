package com.smuniov.addressbook.controller;

import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address-book/persons")
@Log4j2
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDto> personList(){
        log.info("/address-book/persons -GET called");
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDto getById(@PathVariable("id") String id){
        log.info("/address-book/persons/" + id + " -GET called");
        return personService.getById(id);
    }

    @GetMapping("/person/")
    public PersonDto getByEmail(@RequestParam("email") String email){
        log.info("/address-book/persons/" + email + " -GET is called");
        return personService.getByEmail(email);
    }

    @DeleteMapping("/person/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("/address-book/persons/" + id + " -DELETE is called");
        personService.deleteById(id);
    }

    @PostMapping
    public PersonDto create(@Valid @RequestBody PersonDto personDto){
        log.info("/address-book/persons/ -POST is called");
        return personService.create(personDto);
    }

    @PutMapping
    public PersonDto update(@Valid @RequestBody PersonDto personDto){
        log.info("/address-book/persons/ -GET is called, person id: " + personDto.getId());
        return personService.update(personDto, true);
    }

}
