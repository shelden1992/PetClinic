package com.udemy.spring.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
public class Owner extends Person {
    @Column(name = "address")
    private String address;
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Pet> pets = new HashSet<>();

    @Override
    public String toString() {
        return new StringJoiner(", ", Owner.class.getSimpleName() + "[", "]")
                .add("address='" + address + "'")
                .add("telephone='" + telephone + "'")
                .add("pets=" + pets)
                .add("id=" + id)
                .toString();
    }

    public Owner(Long id, String firstName, String lastName, String email, String password, String address, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName, email, password);
        this.address = address;
        this.telephone = telephone;
        if (pets != null) {
            this.pets = pets;
        }
    }

    public Owner(String firstName, String lastName, String email, String password, String address, String telephone) {
        super(null, firstName, lastName, email, password);
        this.address = address;
        this.telephone = telephone;
    }

    public Pet getPet(String name) {
        return getPet(name, false);
    }

    public Pet getPet(String name, boolean ignoreNew) {
        for (Pet pet : pets) {
            if (!ignoreNew || pet.isNew()) {
                if (pet.getName().equalsIgnoreCase(name)) {
                    return pet;
                }
            }

        }
        return null;
    }

}
