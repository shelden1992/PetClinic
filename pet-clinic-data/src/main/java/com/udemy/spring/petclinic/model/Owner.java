package com.udemy.spring.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */
@Getter
@Setter
@Entity
@Table(name = "owner")
public class Owner extends Person {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> petList;
}
