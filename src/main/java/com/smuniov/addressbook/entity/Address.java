package com.smuniov.addressbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

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
    @NotBlank
    @Size(min = 1)
    @EqualsAndHashCode.Include
    private String city;

    @Column(name = "street", nullable = false)
    @NotBlank
    @Size(min = 1)
    @EqualsAndHashCode.Include
    private String street;

    @ManyToMany(mappedBy = "addresses")
    private List<Person> personsLiveOnThatAddress;

}
