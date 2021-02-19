package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.entity.Role;
import com.smuniov.addressbook.repository.RoleRepository;
import com.smuniov.addressbook.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getAll() {
        return null;
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void deleteByRoleName(String roleName) {

    }
}
