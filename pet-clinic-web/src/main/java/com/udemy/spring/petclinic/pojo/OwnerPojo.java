package com.udemy.spring.petclinic.pojo;

import com.udemy.spring.petclinic.model.Pet;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shelupets Denys on 17.07.2020.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OwnerPojo {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String telephone;
    private Set<Pet> pets = new HashSet<>();

    public OwnerPojo(String firstName, String lastName, String email, String password, String address, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.telephone = telephone;
    }

}
