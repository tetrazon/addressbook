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
    public static final int START_SEQ = 10000;

    @Id
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 1)
    private String name;

    @OneToMany(mappedBy = "person", orphanRemoval = true,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @ManyToMany
    @JoinTable(name = "person_address",
            joinColumns = @JoinColumn(name = "person_fk"),
            inverseJoinColumns = @JoinColumn(name = "address_fk"))
    private Set<Address> addresses;

    public void removeAddress(Address address){
        this.addresses.removeIf(a->a.equals(address));
        address.getPersonsLiveOnThatAddress().remove(this);
    }

    public void addContact(Contact contact){

        if(contacts == null) {
            contacts = new ArrayList<>();
        }
        contact.setPerson(this);
        contacts.add(contact);
    }

    public void removeContact(Contact contact){

        contacts.remove(contact);
    }


}
