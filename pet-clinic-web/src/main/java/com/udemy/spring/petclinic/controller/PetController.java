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
//@RequestMapping("/owners/{ownerId}")
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

//    @ModelAttribute("owner")
//    public Owner finnById(@PathVariable("ownerId") Long id) {
//        return ownerService.findById(id);
//    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return typeService.findAll();
    }

    @GetMapping("/owners/{ownerId}/pets/new")
    public String createPet(@PathVariable("ownerId") Long id, Model model) {
        Owner owner = ownerService.findById(id);
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return "pet/createOrUpdatePetForm";
    }

    @PostMapping("/owners/{ownerId}/pets/new")
    public String createPet(@PathVariable("ownerId") Long id, @Valid Pet pet,BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("owner", ownerService.findById(id));
            model.put("pet", pet);
            return "pet/createOrUpdatePetForm";
        }
        Owner owner = ownerService.findById(id);
        if (StringUtils.hasLength(pet.getName()) && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "This name already exist");
            return "pet/createOrUpdatePetForm";
        }
        owner.getPets().add(pet);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping("/owners/{ownerId}/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("ownerId") Long id, @PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return "pet/createOrUpdatePetForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/edit")
    public String processUpdateForm(@PathVariable("ownerId") Long id,@PathVariable("petId") Long petId, @Valid Pet pet,PetType type,BindingResult result, Model model) {
        System.err.println(type   + "TYPE!!!!!!!!!!!!");
        System.err.println(pet   + "PET!!!!!!!!!!!!");
        Owner owner = ownerService.findById(id);
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return "pet/createOrUpdatePetForm";
        } else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets")
    public String allPets(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pet/petsList";
    }

}
