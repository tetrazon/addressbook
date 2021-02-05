package com.smuniov.addressbook.mapper;

import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.entity.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ContactMapper.class, AddressMapper.class})
public interface PersonMapper {
    PersonDto personToPersonDto(Person person);
    Person personDtoToPerson(PersonDto personDto);
    List<PersonDto> personsToPersonDtos(List<Person> persons);
}
