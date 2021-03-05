package com.smuniov.addressbook.mapper;

import com.smuniov.addressbook.dto.RoleDto;
import com.smuniov.addressbook.entity.Role;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto roleToRoleDto(Role role);
    Set<RoleDto> rolesToRolesDtos(Set<Role> roleSet);
    Role roleDtoToRole(RoleDto roleDto);
    Set<Role> roleDtosToRoles(Set<RoleDto> roleDtoSet);
}
