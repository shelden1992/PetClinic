//package com.udemy.spring.petclinic.pojo;
//
//import com.udemy.spring.petclinic.model.Pet;
//import com.udemy.spring.petclinic.validation.ValidEmail;
//import lombok.*;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Created by Shelupets Denys on 17.07.2020.
// */
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Component
//public class OwnerPojo {
//    private Long id;
//    private String firstName;
//    private String lastName;
//    @ValidEmail
//    private String email;
//    private String password;
//    private String address;
//    private String telephone;
//    private Set<Pet> pets = new HashSet<>();
//
//
//    public boolean isNew() {
//        return id == null;
//    }
//
//}
