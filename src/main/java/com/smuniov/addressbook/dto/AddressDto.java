package com.smuniov.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddressDto {
    @NotEmpty(message = "city is mandatory")
    @Size(min = 2, max = 100, message = "city must contain at least 2 letters")
    private String city;
    @NotEmpty(message = "city is mandatory")
    @Size(min = 2, max = 100, message = "street must contain at least 2 letters")
    private String street;
}
