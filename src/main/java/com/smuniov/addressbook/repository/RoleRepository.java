package com.smuniov.addressbook.repository;

import com.smuniov.addressbook.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
    void deleteByRoleName(String roleName);
}
