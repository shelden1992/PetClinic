package com.udemy.spring.petclinic.validation;

import com.udemy.spring.petclinic.form.LoginForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Shelupets Denys on 14.07.2020.
 */
@Component
public class LogFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm loginForm = (LoginForm) target;
        if (loginForm.getEmail() == null) {
            errors.rejectValue("email", "Can't be empty");
        } else if (loginForm.getPassword() == null) {
            errors.rejectValue("password", "Can't be empty");
        }
    }

}
