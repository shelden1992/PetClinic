package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.sevices.impl.PetServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shelupets Denys on 19.07.2020.
 */
@Controller
@RequestMapping("/pet")
public class PetController {
    private final PetServiceImpl petService;

    public PetController(PetServiceImpl petService) {
        this.petService = petService;
    }
}
