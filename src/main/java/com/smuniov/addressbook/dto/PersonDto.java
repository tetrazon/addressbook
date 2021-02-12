package com.smuniov.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PersonDto {
    private Integer id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @Valid
    private List<ContactDto> contacts;
    @Valid
    @NotNull(message = "at least one address is mandatory")
    private Set<AddressDto> addresses;
}
