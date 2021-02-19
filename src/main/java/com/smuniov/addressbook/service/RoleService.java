package com.smuniov.addressbook.service;

import com.smuniov.addressbook.entity.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAll();
    Role getByRoleName(String roleName);
    void deleteByRoleName(String roleName);
}
