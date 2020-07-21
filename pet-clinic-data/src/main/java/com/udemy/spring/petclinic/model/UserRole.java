package com.udemy.spring.petclinic.model;

import lombok.Getter;

/**
 * Created by Shelupets Denys on 16.07.2020.
 */
public enum UserRole {
    OWNER("ROLE_OWNER"), VET("ROLE_VET"), ADMIN("ROLE_ADMIN");
    @Getter
    private final String name;

    UserRole(String name) {
        this.name = name;
    }
}
