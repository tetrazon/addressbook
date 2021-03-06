package com.smuniov.addressbook.repository;

import com.smuniov.addressbook.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);
    Page<Person> findAll(Pageable pageable);
}
