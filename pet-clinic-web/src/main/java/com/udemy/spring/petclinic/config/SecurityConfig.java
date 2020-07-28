package com.udemy.spring.petclinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Shelupets Denys on 12.07.2020.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService ownerDetailsService;
    private final UserDetailsService vetDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService ownerDetailsService, UserDetailsService vetDetailsService) {
        this.ownerDetailsService = ownerDetailsService;
        this.vetDetailsService = vetDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/vet/**").hasAnyRole("VET", "ADMIN")
                .antMatchers("/owners/**").hasAnyRole("OWNER", "ADMIN")
                .antMatchers("/admin/**", "/actuator/**").hasRole("ADMIN")


                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/sign-in")
                .loginProcessingUrl("/login-confirm")
                .defaultSuccessUrl("/", false)
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/sign-in?error")
                .and()
                .logout().invalidateHttpSession(true)
                .logoutUrl("/sign-out")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.userDetailsService(ownerDetailsService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(vetDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**",
                        "/fonts/**", "/scss/**", "/sass/**");
    }

}
