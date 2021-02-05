package com.smuniov.addressbook.mapper;

import com.smuniov.addressbook.dto.ContactDto;
import com.smuniov.addressbook.entity.Contact;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactDto contactToContactDto(Contact contact);
    Contact contactDtoToContact(ContactDto contactDto);
    List<ContactDto> contactsToContactDtos(List<Contact> contacts);
    List<Contact> contactDtosToContacts(List<ContactDto> contactDtos);
}
