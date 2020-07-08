package com.udemy.spring.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "type")
public class PetType extends BaseEntity {
    @Column(name = "name")
    private String name;

    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }
}

