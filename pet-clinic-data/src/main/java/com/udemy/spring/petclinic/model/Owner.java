package com.udemy.spring.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */
@Getter
@Setter
@Entity
@Table(name = "owner")
public class Owner extends Person {
    @Column(name = "address")
    private String address;
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Pet> pets = new HashSet<>();

    public Owner(Long id, String firstName, String lastName, String address, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.telephone = telephone;
        if (pets != null) {
            this.pets = pets;
        }
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
