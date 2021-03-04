package com.smuniov.addressbook.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {
    public static final int START_SEQ = 100;

    @Id
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    private Integer id;

    @Column(name = "city", nullable = false)
    @EqualsAndHashCode.Include
    private String city;

    @Column(name = "street", nullable = false)
    @EqualsAndHashCode.Include
    private String street;

    @ManyToMany(mappedBy = "addresses")
    private List<Person> persons;

}
