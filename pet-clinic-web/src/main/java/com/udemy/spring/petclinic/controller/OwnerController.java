package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.model.Owner;
import com.udemy.spring.petclinic.service.PasswordService;
import com.udemy.spring.petclinic.sevices.impl.OwnerServiceImpl;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

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
    }

    @GetMapping("owners/{ownerId}/edit")
    public String editOwner(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owner/newOrUpdateUser";
    }

    @GetMapping("/owners/new")
    public String creationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owner/newOrUpdateUser";

    }

    @PostMapping("/owners/new")
    public String processingCreationForm(@Valid @ModelAttribute("owner") Owner owner, BindingResult error, Model model) {
        if (error.hasErrors()) {
            return "owner/newOrUpdateUser";
        }
        if (ownerService.emailExist(owner.getEmail()) && !owner.getEmail().equals(ownerService.findById(owner.getId()).getEmail())) {
            error.rejectValue("email", "email.registration", "This email already exist");
            return "owner/newOrUpdateUser";
        }
        owner.setPassword(passwordService.passwordEncode(owner.getPassword()));
        Owner saveOwner = ownerService.save(owner);
        return "redirect:/owners/" + saveOwner.getId();
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId, SecurityContextHolder securityContextHolder) {
        ModelAndView mav = new ModelAndView("owner/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/owners/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("owner", Owner.builder().build());
        return "owner/findOwners";
    }

    @GetMapping("/owners")
    public String processingFindForm(Owner owner, BindingResult result, Map<String, Object> map) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> owners = ownerService.findAllByLastNameLike(owner.getLastName());
        if (owners.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owner/findOwners";
        }
        if (owners.size() == 1) {
            return "redirect:/owners/" + owners.get(0).getId();
        }
        map.put("owners", owners);
        return "owner/ownersList";

    }
}
