package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.form.LoginForm;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by Shelupets Denys on 13.07.2020.
 */
@Controller
public class LoginController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping(value = "/processing-login")
    public String processingLogin(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult error, Model model) {
        if (error.hasErrors()) {
            return "login";
        }
        return "redirect:";

    }

    @GetMapping(value = "/login-form")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
}
