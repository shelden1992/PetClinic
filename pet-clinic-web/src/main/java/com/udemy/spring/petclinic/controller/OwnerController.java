package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.sevices.impl.OwnerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shelupets Denys on 09.07.2020.
 */
@Controller
@RequestMapping(value = "/owner")
public class OwnerController {

    private final OwnerServiceImpl service;

    public OwnerController(OwnerServiceImpl service) {
        this.service = service;
    }

    @GetMapping({"/list"})
    public String ownerList(Model model) {
        model.addAttribute("onwers", service.findAll());
        return "ownerList";
    }
}
