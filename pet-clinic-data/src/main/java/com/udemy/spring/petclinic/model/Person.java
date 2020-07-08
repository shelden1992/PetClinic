package com.udemy.spring.petclinic.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */

@Getter
@Setter
public class Person extends BaseEntity {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
}
