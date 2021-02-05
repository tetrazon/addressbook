package com.smuniov.addressbook.repository;

import com.smuniov.addressbook.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface JpaPersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByName(String name);

    /*@Query(
            value = "DELETE FROM address " +
                    "USING person_address " +
                    "WHERE address.id = person_address.address_fk " +
                    "               AND person_address.person_fk = ?1",
            nativeQuery = true)*/
    @Query(
            value = "DELETE FROM address a"
                    + "    USING (select address_fk as addr_fk from\n"
                    + "        (SELECT address_fk, count(address_fk)\n"
                    + "        FROM person_address\n"
                    + "        GROUP BY person_address.address_fk\n"
                    + "        HAVING count(*) = 1) per_addr\n"
                    + "        join (select address_fk as fk from person_address where person_fk = ?1) one\n"
                    + "        ON per_addr.address_fk = one.fk\n" + "        ) p_a\n" + "WHERE a.id = p_a.addr_fk;",
            nativeQuery = true)
    @Transactional
    @Modifying(clearAutomatically = true)
    void deleteAddressesReferringOnlyToThisPerson(int id);
}
