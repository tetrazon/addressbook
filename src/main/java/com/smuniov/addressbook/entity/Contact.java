package com.smuniov.addressbook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Contact {
    private static final int START_SEQ = 100;

    @Id
    @SequenceGenerator(name = "contact_seq", sequenceName = "contact_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq")
    private Integer id;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_fk")
    private Person person;
}
