package com.udemy.spring.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@Table(name = "type")
public class PetType {
    @Id
    @Column(name = "name")
    private String name;

    public PetType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

