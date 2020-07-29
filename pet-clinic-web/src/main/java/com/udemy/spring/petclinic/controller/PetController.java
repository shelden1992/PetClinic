package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.model.Owner;
import com.udemy.spring.petclinic.model.Pet;
import com.udemy.spring.petclinic.model.PetType;
import com.udemy.spring.petclinic.sevices.interfaces.OwnerService;
import com.udemy.spring.petclinic.sevices.interfaces.PetService;
import com.udemy.spring.petclinic.sevices.interfaces.PetTypeService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by Shelupets Denys on 19.07.2020.
 */
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService typeService;

    public PetController(PetService petService1, OwnerService ownerService, PetTypeService typeService) {
        this.petService = petService1;
        this.ownerService = ownerService;
        this.typeService = typeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("owner")
    public Owner finnById(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return typeService.findAll();
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return "pet/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("pet", pet);
            return "pet/createOrUpdatePetForm";
        }
        if (StringUtils.hasLength(pet.getName()) && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "This name already exist");
            return "pet/createOrUpdatePetForm";
        }
        owner.getPets().add(pet);
        pet.setOwner(owner);
        ownerService.saveOrUpdate(owner);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, ModelMap model) {
        model.put("pet", petService.findById(petId));
        return "pet/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Owner owner, @PathVariable("petId") Long petId, @Valid Pet pet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return "pet/createOrUpdatePetForm";
        } else {
            pet.setOwner(owner);
            petService.saveOrUpdate(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }


}
