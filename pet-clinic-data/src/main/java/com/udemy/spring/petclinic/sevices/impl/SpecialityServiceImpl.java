package com.udemy.spring.petclinic.sevices.impl;

import com.udemy.spring.petclinic.model.Speciality;
import com.udemy.spring.petclinic.repositories.SpecialityRepository;
import com.udemy.spring.petclinic.sevices.interfaces.SpecialityService;
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
public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityRepository repository;

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        repository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Speciality findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Speciality saveOrUpdate(Speciality object) {
        return repository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        repository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }
}
