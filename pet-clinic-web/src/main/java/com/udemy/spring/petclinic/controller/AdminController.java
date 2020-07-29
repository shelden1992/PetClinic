package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.model.Owner;
import com.udemy.spring.petclinic.sevices.interfaces.OwnerService;
import com.udemy.spring.petclinic.sevices.interfaces.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Shelupets Denys on 29.07.2020.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final OwnerService ownerService;
    private final PetService petService;

    @Autowired
    public AdminController(OwnerService ownerService, PetService petService) {
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @GetMapping("/pets")
    public String allPets(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pet/petsList";
    }

    @GetMapping("/owners")
    public String processingFindForm(Owner owner, BindingResult result, Map<String, Object> map) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
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
