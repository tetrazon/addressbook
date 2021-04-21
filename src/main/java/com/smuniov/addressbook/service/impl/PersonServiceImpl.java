package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.dto.PersonDto;
import com.smuniov.addressbook.entity.Person;
import com.smuniov.addressbook.exceptions.BadDataException;
import com.smuniov.addressbook.mapper.PersonMapper;
import com.smuniov.addressbook.repository.JpaPersonRepository;
import com.smuniov.addressbook.service.AddressService;
import com.smuniov.addressbook.service.ContactService;
import com.smuniov.addressbook.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class PersonServiceImpl implements PersonService {

    //private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final JpaPersonRepository personRepository;
    private final ContactService contactService;
    private final AddressService addressService;
    private final PersonMapper personMapper;

    public PersonServiceImpl(JpaPersonRepository personRepository,
                             ContactService contactService,
                             AddressService addressService,
                             PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.contactService = contactService;
        this.addressService = addressService;
        this.personMapper = personMapper;
    }

    @Override
    //@Transactional
    public List<PersonDto> getAll() {
        return personMapper.personsToPersonDtos(personRepository.findAll());
    }

    @Override
    public ResponseEntity<Map<String, Object>> getAll(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Person> personPage = personRepository.findAll(paging);
            List<PersonDto> personDtos = personMapper.personsToPersonDtos(personPage.getContent());
            Map<String, Object> response = new HashMap<>();
            response.put("personDtos", personDtos);
            response.put("currentPage", personPage.getNumber());
            response.put("totalItems", personPage.getTotalElements());
            response.put("totalPages", personPage.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    //@Transactional
    public PersonDto getById(String id) {
        int intId = -1;
        try {
            intId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new BadDataException("id is not a valid number");
        }
        return personMapper.personToPersonDto(personRepository.findById(intId).orElseThrow(() ->
                new BadDataException("id is not found!")));
    }

    @Override
    //@Transactional
    public PersonDto getByEmail(String email) {
        return personMapper.personToPersonDto(personRepository.findByEmail(email).orElseThrow(() ->
                new BadDataException("wrong email! The person with such email is not found")));
    }

    @Override
    //@Transactional
    public void deleteById(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (!optionalPerson.isPresent()) {
            throw new BadDataException("person with id " + id + " not found");
        }
        Person personToDelete = optionalPerson.get();
        addressService.deleteNonUsedAddresses(personToDelete.getAddresses());
        personRepository.delete(personToDelete);

    }

    @Override
    //@Transactional
    public ResponseEntity<PersonDto> create(PersonDto personDto, HttpServletRequest request) {
        if (!ObjectUtils.isEmpty(personDto.getId())) {
            throw new BadDataException("id in person when creating: delete id from person first!");
        }
        return update(personDto, request, false);
    }

    @Override
    //@Transactional
    public ResponseEntity<PersonDto> update(PersonDto personDto, HttpServletRequest request, boolean isUpdate) {
        Person personFromDto = personMapper.personDtoToPerson(personDto);
        Person personToSave = new Person();
        Optional<Person> optionalPersonFromDb;
        Integer personId = personDto.getId();
        if (isUpdate) {
            if (!ObjectUtils.isEmpty(personId) && personId >= 1) {
                optionalPersonFromDb = personRepository.findById(personId);
                personToSave = optionalPersonFromDb.orElseThrow(() ->
                        new BadDataException("Wrong id in findById(personId) method"));
            } else {
                throw new BadDataException("person without id when updating: person to update must contain id");
            }
        }
        personToSave.setAddresses(addressService.getUpdatedAddresses(personFromDto.getAddresses(),
                                                                        personToSave.getAddresses()));
        contactService.clearContacts(personToSave);
        personToSave.setContacts(contactService.getContactsWithPerson(personToSave, personFromDto.getContacts()));
        personToSave.setName(personFromDto.getName());
        personToSave.setEmail(personFromDto.getEmail());
        personRepository.save(personToSave);
        return ResponseEntity
                .created(URI.create(request.getRequestURI() + "/" + personToSave.getId()))
                .body(personMapper.personToPersonDto(personToSave));
    }

}
