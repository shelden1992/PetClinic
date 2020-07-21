package com.udemy.spring.petclinic.service;

import com.udemy.spring.petclinic.model.Person;
import com.udemy.spring.petclinic.model.Speciality;
import com.udemy.spring.petclinic.model.UserRole;
import com.udemy.spring.petclinic.model.Vet;
import com.udemy.spring.petclinic.sevices.interfaces.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

/**
 * Created by Shelupets Denys on 16.07.2020.
 */
@Service
public class VetDetailsService implements UserDetailsService {
    private final VetService vetService;

    @Autowired
    public VetDetailsService(VetService vetService) {
        this.vetService = vetService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Vet vet = vetService.findByEmail(email);
        if (isNull(vet)) {
            throw new UsernameNotFoundException("Email not find");
        }

        return buildUserForAuthentication(vet, getSimpleGrantedAuthorities(vet));
    }

    private Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(Vet vet) {
        Set<SimpleGrantedAuthority> userAuthority = new HashSet<>();
        if (vet.getSpecialities().stream().anyMatch(Speciality::isAdmin)) {
            userAuthority.add(new SimpleGrantedAuthority(UserRole.ADMIN.getName()));
            return userAuthority;
        }
        userAuthority.add(new SimpleGrantedAuthority(UserRole.VET.getName()));
        return userAuthority;
    }

    private UserDetails buildUserForAuthentication(Person person, Set<SimpleGrantedAuthority> userAuthority) {
        return new org.springframework.security.core.userdetails.User(person.getFirstName(), person.getPassword(), true, true, true, true, userAuthority);

    }

}
