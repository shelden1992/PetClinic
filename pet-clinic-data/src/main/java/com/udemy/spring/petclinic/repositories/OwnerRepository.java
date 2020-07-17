package com.udemy.spring.petclinic.repositories;

import com.udemy.spring.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Shelupets Denys on 08.07.2020.
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

    Owner findByEmail(String email);
}
