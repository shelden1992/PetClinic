package com.udemy.spring.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vet")
public class Vet extends Person {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities;
}
