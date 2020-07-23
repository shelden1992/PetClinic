package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.model.UserRole;
import com.udemy.spring.petclinic.security.SecurityService;
import com.udemy.spring.petclinic.sevices.interfaces.OwnerService;
import com.udemy.spring.petclinic.sevices.interfaces.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Shelupets Denys on 10.07.2020.
 */
@Controller
public class StaticResourceController {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final SecurityService securityService;

    @Autowired
    public StaticResourceController(OwnerService ownerService, VetService vetService, SecurityService securityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.securityService = securityService;
    }

    @GetMapping({"/home", "/index", "/"})
    public String homePage(Model model) {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (authentication.contains(UserRole.OWNER.getName())) {
            model.addAttribute("owner", ownerService.findById(securityService.getId(authentication)));
            return "owner/index";
        } else if (authentication.contains(UserRole.VET.getName())) {
            model.addAttribute("vet", vetService.findById(securityService.getId(authentication)));
            return "vet/index";
        } else if (authentication.contains(UserRole.ADMIN.getName())) {
            return "index";
        }
        return "index";
    }



    @GetMapping({"/about"})
    public String aboutPage() {
        return "about";
    }

    @GetMapping({"/gallery"})
    public String galleryPage() {
        return "gallery";
    }

    @GetMapping({"/blog"})
    public String teamPage() {
        return "blog";
    }

    @GetMapping({"/services"})
    public String servicesPage() {
        return "services";
    }
}
