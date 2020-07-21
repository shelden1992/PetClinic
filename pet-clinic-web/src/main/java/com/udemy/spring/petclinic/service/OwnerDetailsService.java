package com.udemy.spring.petclinic.service;

import com.udemy.spring.petclinic.model.Owner;
import com.udemy.spring.petclinic.model.Person;
import com.udemy.spring.petclinic.model.UserRole;
import com.udemy.spring.petclinic.sevices.interfaces.OwnerService;
import lombok.extern.slf4j.Slf4j;
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
 * Created by Shelupets Denys on 12.07.2020.
 */
@Slf4j
@Service
public class OwnerDetailsService implements UserDetailsService {

    private final OwnerService ownerService;

    @Autowired
    public OwnerDetailsService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner = ownerService.findByEmail(email);
        if (isNull(owner)) {
            throw new UsernameNotFoundException("Email not find");
        }

        return buildUserForAuthentication(owner, getSimpleGrantedAuthorities());
    }

    private Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities() {
        Set<SimpleGrantedAuthority> userAuthority = new HashSet<>();
        userAuthority.add(new SimpleGrantedAuthority(UserRole.OWNER.getName()));
        return userAuthority;
    }

    private UserDetails buildUserForAuthentication(Person person, Set<SimpleGrantedAuthority> userAuthority) {
        return new org.springframework.security.core.userdetails.User(person.getFirstName(), person.getPassword(), true, true, true, true, userAuthority);

    }

}
