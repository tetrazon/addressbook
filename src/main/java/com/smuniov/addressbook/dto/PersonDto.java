package com.smuniov.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PersonDto {
    private Integer id;
    private String name;
    private List<ContactDto> contacts;
    private Set<AddressDto> addresses;
}
