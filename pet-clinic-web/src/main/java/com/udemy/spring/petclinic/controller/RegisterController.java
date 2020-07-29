package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.model.Owner;
import com.udemy.spring.petclinic.service.PasswordService;
import com.udemy.spring.petclinic.sevices.impl.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Shelupets Denys on 28.07.2020.
 */
@Controller
public class RegisterController {
    private final OwnerServiceImpl ownerService;
    private final PasswordService passwordService;

    @Autowired
    public RegisterController(OwnerServiceImpl ownerService, PasswordService passwordService) {
        this.ownerService = ownerService;
        this.passwordService = passwordService;
    }

    @GetMapping("/new")
    public String creationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owner/newOrUpdateUser";

    }

    @PostMapping("/new")
    public String processingCreationForm(@Valid @ModelAttribute("owner") Owner owner, BindingResult error, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (error.hasErrors()) {
            return "owner/newOrUpdateUser";
        }
        if (owner.isNew() && ownerService.emailExist(owner.getEmail())) {
            error.rejectValue("email", "email.registration", "This email already exist");
            return "owner/newOrUpdateUser";
        } else {
            if (!owner.isNew() && !owner.getEmail().equals(ownerService.findById(owner.getId()).getEmail())) {
                error.rejectValue("email", "email.registration", "This email already exist");
                return "owner/newOrUpdateUser";
            } else if (!owner.isNew()) {
                owner.setPets(ownerService.findById(owner.getId()).getPets());
            }
        }
        owner.setPassword(passwordService.passwordEncode(owner.getPassword()));
        Owner saveOwner = ownerService.saveOrUpdate(owner);
        return "redirect:/owners/" + saveOwner.getId();
    }
}
