package com.udemy.spring.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Shelupets Denys on 08.07.2020.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "speciality")
public class Speciality extends BaseEntity {
    @Column(name = "description")
    private String description;

    public Speciality(Long id, String description) {
        super(id);
        this.description = description;

    }
}
