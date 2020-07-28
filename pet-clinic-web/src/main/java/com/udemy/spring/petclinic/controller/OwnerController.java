package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.model.Owner;
import com.udemy.spring.petclinic.model.UserRole;
import com.udemy.spring.petclinic.security.SecurityService;
import com.udemy.spring.petclinic.service.PasswordService;
import com.udemy.spring.petclinic.sevices.impl.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by Shelupets Denys on 09.07.2020.
 */
@Controller
public class OwnerController {

    private final OwnerServiceImpl ownerService;
    private final PasswordService passwordService;
    private final SecurityService securityService;

    @Autowired
    public OwnerController(OwnerServiceImpl ownerService, PasswordService passwordService, SecurityService securityService) {
        this.ownerService = ownerService;
        this.passwordService = passwordService;
        this.securityService = securityService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("owners/{ownerId}/edit")
    public String editOwner(@PathVariable Long ownerId, Model model) throws Exception {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (!securityService.getId(authentication).equals(ownerId)) {
            throw new Exception("Not valid");
        }
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owner/newOrUpdateUser";
    }

    @GetMapping("/owners/new")
    public String creationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owner/newOrUpdateUser";

    }

    @PostMapping("/owners/new")
    public String processingCreationForm(@Valid @ModelAttribute("owner") Owner owner, BindingResult error, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (error.hasErrors()) {
            return "owner/newOrUpdateUser";
        }
        if (ownerService.emailExist(owner.getEmail()) && !owner.getEmail().equals(ownerService.findById(owner.getId()).getEmail())) {
            error.rejectValue("email", "email.registration", "This email already exist");
            return "owner/newOrUpdateUser";
        }
        owner.setPassword(passwordService.passwordEncode(owner.getPassword()));
        Owner saveOwner = ownerService.save(owner);
        authenticateUserAndSetSession(owner, request);
        return "redirect:/owners/" + saveOwner.getId();
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) throws Exception {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if ((!securityService.getId(authentication).equals(ownerId)) && !authentication.contains(UserRole.ADMIN.getName())) {
            throw new Exception("Not valid");
        }
        ModelAndView mav = new ModelAndView("owner/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/owners/details")
    public ModelAndView showLoginOwner() throws Exception {
        Long id = securityService.getId();
        ModelAndView mav = new ModelAndView("owner/ownerDetails");
        mav.addObject(ownerService.findById(id));
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

    private void authenticateUserAndSetSession(Owner owner, HttpServletRequest request) {
        String username = owner.getEmail();
        String password = owner.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
//        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
