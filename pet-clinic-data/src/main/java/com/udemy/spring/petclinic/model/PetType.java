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
@Table(name = "type")
public class PetType extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> petList;
    @Column(name = "name")
    private String name;

}
