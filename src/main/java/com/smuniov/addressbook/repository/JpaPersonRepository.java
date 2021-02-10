package com.smuniov.addressbook.repository;

import com.smuniov.addressbook.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JpaPersonRepository extends JpaRepository<Person, Integer> {
    Optional<List<Person>> findByName(String name);
}
