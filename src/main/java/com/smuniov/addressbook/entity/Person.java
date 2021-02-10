package com.smuniov.addressbook.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private static final int START_SEQ = 100;

    @Id
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 1)
    private String name;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL) //orphanRemoval = true,
    private List<Contact> contacts;

    @ManyToMany(cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "person_address",
            joinColumns = @JoinColumn(name = "person_fk"),
            inverseJoinColumns = @JoinColumn(name = "address_fk"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<Address> addresses;

}
