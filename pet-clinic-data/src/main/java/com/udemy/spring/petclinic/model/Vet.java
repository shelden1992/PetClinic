package com.udemy.spring.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Shelupets Denys on 07.07.2020.
 */
@Entity
@Table(name = "vet")
public class Vet extends Person {
}
