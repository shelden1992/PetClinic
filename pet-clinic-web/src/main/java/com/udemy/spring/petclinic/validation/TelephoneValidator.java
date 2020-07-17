package com.udemy.spring.petclinic.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shelupets Denys on 19.07.2020.
 */
public class TelephoneValidator implements ConstraintValidator<ValidTelephone, String> {
    private static final String PATTERN = "[+]d{2}d{2}d{4}d{4}";


    @Override
    public boolean isValid(String telephone, ConstraintValidatorContext context) {
        if (telephone == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }
}
