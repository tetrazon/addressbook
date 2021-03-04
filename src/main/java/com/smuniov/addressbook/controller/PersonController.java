package com.smuniov.addressbook.controller;

import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address-book/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonDto> personList(){
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public PersonDto getById(@PathVariable("id") String id){
        return personService.getById(id);
    }

    @GetMapping("/person/")
    public PersonDto getByEmail(@RequestParam("email") String email){
        return personService.getByEmail(email);
    }

    @DeleteMapping("/person/{id}")
    public void delete(@PathVariable("id") int id) {
        personService.deleteById(id);
    }

    @PostMapping
    public PersonDto create(@Valid @RequestBody PersonDto personDto){
        return personService.create(personDto);
    }

    @PutMapping
    public PersonDto update(@Valid @RequestBody PersonDto personDto){
        return personService.update(personDto, true);
    }

}
