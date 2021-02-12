package com.smuniov.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ContactDto {
    @Size(min = 13, max = 20, message = "a telephone number must contain at least 13 letters: +375...")
    private String telephone;
}
