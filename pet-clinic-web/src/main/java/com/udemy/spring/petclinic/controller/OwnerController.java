package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.model.Owner;
import com.udemy.spring.petclinic.service.PasswordService;
import com.udemy.spring.petclinic.sevices.impl.OwnerServiceImpl;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Shelupets Denys on 09.07.2020.
 */
@Controller
public class OwnerController {

    private final OwnerServiceImpl ownerService;
    private final PasswordService passwordService;

    public OwnerController(OwnerServiceImpl ownerService, PasswordService passwordService) {
        this.ownerService = ownerService;
        this.passwordService = passwordService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"admin/allOwners"})
    public String ownerList(Model model) {
        model.addAttribute("onwers", ownerService.findAll());
        return "ownerList";
    }

    @GetMapping("/{ownerId}/edit")
    public String editOwner(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "newOrUpdateUser";
    }

    @GetMapping("/new")
    public String creationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "newOrUpdateUser";

    }

    @PostMapping("/new")
    public String processingCreationForm(@Valid @ModelAttribute("owner") Owner owner, BindingResult error, Model model) {
        if (error.hasErrors()) {
            return "newOrUpdateUser";
        }
        if (ownerService.emailExist(owner.getEmail())) {
            error.rejectValue("email", "email.registration", "This email already exist");
            return "newOrUpdateUser";
        }
        owner.setPassword(passwordService.passwordEncode(owner.getPassword()));
        Owner saveOwner = ownerService.save(owner);
        return "redirect:/owners/" + saveOwner.getId();
    }
}
