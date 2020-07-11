package com.udemy.spring.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Shelupets Denys on 10.07.2020.
 */
@Controller
public class StaticResourceController {
    @GetMapping({"/home", "/index"})
    public String homePage() {
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
