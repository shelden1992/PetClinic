package com.udemy.spring.petclinic.controller;

import com.udemy.spring.petclinic.model.Owner;
import com.udemy.spring.petclinic.model.UserRole;
import com.udemy.spring.petclinic.security.SecurityService;
import com.udemy.spring.petclinic.sevices.impl.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by Shelupets Denys on 09.07.2020.
 */
@Controller
public class OwnerController {

    private final OwnerServiceImpl ownerService;
    private final SecurityService securityService;

    @Autowired
    public OwnerController(OwnerServiceImpl ownerService, SecurityService securityService) {
        this.ownerService = ownerService;
        this.securityService = securityService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String editOwner(@PathVariable Long ownerId, Model model) throws Exception {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (!securityService.getId(authentication).equals(ownerId)) {
            throw new Exception("Not valid");
        }
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owner/newOrUpdateUser";
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


//    private void authenticateUserAndSetSession(Owner owner, HttpServletRequest request) {
//        String username = owner.getEmail();
//        String password = owner.getPassword();
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//
//        // generate session if one doesn't exist
//        request.getSession();
//
//        token.setDetails(new WebAuthenticationDetails(request));
////        Authentication authenticatedUser = authenticationManager.authenticate(token);
//
//        SecurityContextHolder.getContext().setAuthentication(token);
//    }
}
