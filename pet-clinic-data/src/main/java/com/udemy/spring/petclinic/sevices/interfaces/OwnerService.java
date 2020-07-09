package com.udemy.spring.petclinic.sevices.interfaces;

import com.udemy.spring.petclinic.model.Owner;

import java.util.List;

/**
 * Created by Shelupets Denys on 08.07.2020.
 */
public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
