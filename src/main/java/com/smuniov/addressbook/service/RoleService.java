package com.smuniov.addressbook.service;

import com.smuniov.addressbook.dto.UserDto;
import com.smuniov.addressbook.entity.Role;
import com.smuniov.addressbook.entity.RoleName;
import com.smuniov.addressbook.entity.User;

public interface RoleService {
    Role getByRoleName(RoleName roleName);
    void deleteByRoleName(String roleName);
    void setRoles(UserDto userDto, User user);
}
