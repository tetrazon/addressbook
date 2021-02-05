package com.smuniov.addressbook.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @NotBlank
    @Size(min = 13)
    private String telephone;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_fk")
    private Person person;
}
