package com.udemy.spring.petclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pet")
public class Pet extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @ManyToOne
    @JoinColumn(
            name = "type_name",
            referencedColumnName = "name")
    private PetType petType;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    public Pet(Long id, LocalDate birthDate, String name, PetType petType, Owner owner, Set<Visit> visits) {
        super(id);
        this.birthDate = birthDate;
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        if (visits != null && visits.size() > 0) {
            this.visits = visits;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pet.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("birthDate=" + birthDate)
                .add("petType=" + petType)
                .add("visits=" + visits)
                .add("id=" + id)
                .toString();
    }
}
