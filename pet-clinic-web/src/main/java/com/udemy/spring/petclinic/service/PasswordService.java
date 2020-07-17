package com.udemy.spring.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Shelupets Denys on 16.07.2020.
 */
@Service
public class PasswordService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean passwordSame(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    public String passwordEncode (CharSequence rawPassword ){
        return passwordEncoder.encode(rawPassword);
    }
}
