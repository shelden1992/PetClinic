package com.udemy.spring.petclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.StringJoiner;

/**
 * Created by Shelupets Denys on 08.07.2020.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visit")
public class Visit extends BaseEntity {
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "date_visit")
    private LocalDate dateVisit;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @Override
    public String toString() {
        return new StringJoiner(", ", Visit.class.getSimpleName() + "[", "]")
                .add("dateVisit=" + dateVisit)
                .add("description='" + description + "'")
                .add("id=" + id)
                .toString();
    }
}
