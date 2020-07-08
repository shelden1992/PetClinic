package com.udemy.spring.petclinic.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */
@Getter
@Setter
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthDate;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

}
