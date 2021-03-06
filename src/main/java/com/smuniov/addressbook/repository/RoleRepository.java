package com.smuniov.addressbook.repository;

import com.smuniov.addressbook.entity.Role;
import com.smuniov.addressbook.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //Role findByRoleName(String roleName);
    Role findByRoleName(RoleName roleName);
    void deleteByRoleName(String roleName);
}
