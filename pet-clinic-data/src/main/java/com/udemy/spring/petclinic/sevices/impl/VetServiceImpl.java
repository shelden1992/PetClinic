package com.udemy.spring.petclinic.sevices.impl;

import com.udemy.spring.petclinic.model.Vet;
import com.udemy.spring.petclinic.repositories.VetRepository;
import com.udemy.spring.petclinic.sevices.interfaces.VetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;


/**
 * Created by Shelupets Denys on 09.07.2020.
 */
@Slf4j
@AllArgsConstructor
@Service
public class VetServiceImpl implements VetService {
    private final VetRepository repository;

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        repository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return repository.save(object);
    }

    @Override
    public void delete(Vet object) {
        repository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    @Override
    public Vet findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean emailExist(String email) {
        return nonNull(findByEmail(email));
    }
}
