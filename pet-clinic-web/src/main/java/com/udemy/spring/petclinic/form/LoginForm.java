package com.udemy.spring.petclinic.form;

import com.udemy.spring.petclinic.model.UserRole;
import com.udemy.spring.petclinic.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Shelupets Denys on 13.07.2020.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoginForm {
    @ValidEmail
    @NotNull(message = "error.cant.be.empty")
    @Size(min = 4, message = "error.cant.be.empty")
    private String email;
    @NotNull
    @Size(min = 5, message = "error.cant.be.empty")
    private String password;
    private UserRole userRole;


}
