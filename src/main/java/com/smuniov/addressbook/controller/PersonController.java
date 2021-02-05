package com.smuniov.addressbook.controller;

import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.service.PersonService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("all")
    public List<PersonDto> personList(){
        return personService.getAll();
    }
    @GetMapping("{id}")
    public PersonDto getById(@PathVariable("id") String id){
        return personService.getById(id);
    }

    @GetMapping
    public PersonDto getByName(@RequestParam("name") String name){
        return personService.getByName(name);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id) {
        personService.deleteById(id);
    }

    @PostMapping
    public PersonDto create(@RequestBody PersonDto personDto){
        return personService.create(personDto);
    }

    @PutMapping
    public PersonDto update(@RequestBody PersonDto personDto){
        return personService.update(personDto);
    }

}
