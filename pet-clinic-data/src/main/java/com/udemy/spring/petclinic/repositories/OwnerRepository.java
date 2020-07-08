package com.udemy.spring.petclinic.repositories;

import com.udemy.spring.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Shelupets Denys on 08.07.2020.
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
