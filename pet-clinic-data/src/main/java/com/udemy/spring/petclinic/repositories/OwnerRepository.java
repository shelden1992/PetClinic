package com.udemy.spring.petclinic.repositories;

import com.udemy.spring.petclinic.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Shelupets Denys on 08.07.2020.
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);

    @Query("select distinct owner from Owner owner left join fetch owner.pets where owner.lastName like :lastName%")
    @Transactional(readOnly = true)
    Collection<Owner> findAllByLastNameLike(@Param("lastName") String lastName);

    Owner findByEmail(String email);
}
