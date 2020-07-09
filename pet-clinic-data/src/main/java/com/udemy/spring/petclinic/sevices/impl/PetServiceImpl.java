package com.udemy.spring.petclinic.sevices.impl;

import com.udemy.spring.petclinic.model.Pet;
import com.udemy.spring.petclinic.repositories.PetRepository;
import com.udemy.spring.petclinic.sevices.interfaces.PetService;
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
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Override
    public Set<Pet> findAll() {
        HashSet<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).get();
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
