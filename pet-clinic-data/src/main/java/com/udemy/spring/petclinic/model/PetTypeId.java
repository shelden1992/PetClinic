package com.udemy.spring.petclinic.model;

import java.io.Serializable;

/**
 * Created by Shelupets Denys on 24.07.2020.
 */
public class PetTypeId implements Serializable {
    private Long id;
    private String name;

    public PetTypeId() {
    }

    public PetTypeId(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}
