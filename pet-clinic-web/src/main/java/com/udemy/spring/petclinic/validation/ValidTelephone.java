package com.udemy.spring.petclinic.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Shelupets Denys on 19.07.2020.
 */
@Constraint(validatedBy = TelephoneValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidTelephone {
    String message() default "Invalid phone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
