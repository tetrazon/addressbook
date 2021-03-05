package com.smuniov.addressbook.repository;

import com.smuniov.addressbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);
}
