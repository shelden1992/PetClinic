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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vet")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "vet")
    private Set<WorkProcessing> workProcessing = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "vet")
    private Set<Visit> visits;


    @Override
    public String toString() {
        return new StringJoiner(", ", Vet.class.getSimpleName() + "[", "]")
                .add("specialities=" + specialities)
                .add("workProcessing=" + workProcessing)
                .add("visits=" + visits)
                .add("id=" + id)
                .toString();
    }
}
