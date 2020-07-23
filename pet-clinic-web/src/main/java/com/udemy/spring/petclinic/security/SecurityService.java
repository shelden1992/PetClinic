package com.udemy.spring.petclinic.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Shelupets Denys on 23.07.2020.
 */
@Service
public class SecurityService {
    public Long getId(String authentication) {
        return Long.valueOf(authentication.substring(authentication.indexOf("id=") + 3, authentication.length() - 1));
    }

    public Long getId() {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return Long.valueOf(authentication.substring(authentication.indexOf("id=") + 3, authentication.length() - 1));
    }
}
