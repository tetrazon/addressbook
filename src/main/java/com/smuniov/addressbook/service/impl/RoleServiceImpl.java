package com.smuniov.addressbook.service.impl;

import com.smuniov.addressbook.dto.RoleDto;
import com.smuniov.addressbook.dto.UserDto;
import com.smuniov.addressbook.entity.Role;
import com.smuniov.addressbook.entity.User;
import com.smuniov.addressbook.mapper.RoleMapper;
import com.smuniov.addressbook.repository.RoleRepository;
import com.smuniov.addressbook.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void deleteByRoleName(String roleName) {
        roleRepository.deleteByRoleName(roleName);
    }

    @Override
    public void setRoles(UserDto userDto, User user) {
        Set<RoleDto> userDtoRoles = userDto.getRoles();
        Set<Role> roles = new HashSet<>();
        if (userDtoRoles == null || userDtoRoles.isEmpty()){
            Role role = roleRepository.findByRoleName("USER");
            roles = Collections.singleton(role);
        } else {
            for (RoleDto roleDto: userDtoRoles){
                Role roleFromDb = roleRepository.findByRoleName(roleDto.getRoleName());
                if (roleFromDb == null){
                    roles.add(roleMapper.roleDtoToRole(roleDto));
                } else {
                    roles.add(roleFromDb);
                }
            }
        }
        user.setRoles(roles);
    }
}
