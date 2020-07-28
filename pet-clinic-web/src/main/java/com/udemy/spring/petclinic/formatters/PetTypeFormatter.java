package com.udemy.spring.petclinic.formatters;

import com.udemy.spring.petclinic.model.PetType;
import com.udemy.spring.petclinic.sevices.interfaces.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Shelupets Denys on 24.07.2020.
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService service;

    @Autowired
    public PetTypeFormatter(PetTypeService service) {
        this.service = service;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        for (PetType type : service.findAll()) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

    @Override
    public String print(PetType type, Locale locale) {
        return type.toString();
    }
}
