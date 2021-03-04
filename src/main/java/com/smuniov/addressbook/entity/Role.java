package com.smuniov.addressbook.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {
    @Id
    @SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    private Long id;
    @NotNull
    @NotBlank
    @Column(name = "role_name", columnDefinition = "enum('ADMIN','USER','EDITOR')")
    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
    //private String roleName;
}
