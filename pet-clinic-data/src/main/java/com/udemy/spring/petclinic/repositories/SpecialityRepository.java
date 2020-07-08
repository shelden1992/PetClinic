package com.udemy.spring.petclinic.repositories;

import com.udemy.spring.petclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Shelupets Denys on 08.07.2020.
 */
public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
