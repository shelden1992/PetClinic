package com.udemy.spring.petclinic.repositories;

import com.udemy.spring.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
    Vet findByEmail(String email);
}
