package com.udemy.spring.petclinic.sevices.interfaces;

import java.util.Set;

/**
 * Created by Shelupets Denys on 08.07.2020.
 */
public interface CrudService<T, ID> {
    Set<T> findAll();

    T findById(ID id);

    T save (T object);

    void delete (T object);

    void deleteById(ID id);
}
