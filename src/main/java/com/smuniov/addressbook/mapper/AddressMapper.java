package com.smuniov.addressbook.mapper;

import com.smuniov.addressbook.dto.AddressDto;
import com.smuniov.addressbook.entity.Address;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto addressToAddressDto(Address address);
    Address addressDtoToAddress(AddressDto addressDto);
    Set<AddressDto> addressesToAddressDtos(Set<Address> addresses);
    Set<Address> addressDtosToAddresses(Set<AddressDto> addressDtos);
}
