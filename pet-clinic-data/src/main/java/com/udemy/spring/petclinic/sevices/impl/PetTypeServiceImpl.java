package com.udemy.spring.petclinic.sevices.impl;

import com.udemy.spring.petclinic.model.PetType;
import com.udemy.spring.petclinic.repositories.PetTypeRepository;
import com.udemy.spring.petclinic.sevices.interfaces.PetTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shelupets Denys on 09.07.2020.
 */
@Slf4j
@AllArgsConstructor
@Service
public class PetTypeServiceImpl implements PetTypeService {
    private final PetTypeRepository repository;

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        repository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public PetType saveOrUpdate(PetType object) {
        return repository.save(object);
    }

    @Override
    public void delete(PetType object) {
        repository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
