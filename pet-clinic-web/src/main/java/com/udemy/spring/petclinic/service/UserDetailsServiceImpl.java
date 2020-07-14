//package com.udemy.spring.petclinic.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.Collections;
//import java.util.Set;
//
///**
// * Created by Shelupets Denys on 12.07.2020.
// */
//@Slf4j
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Resource
//    private UserServiceImpl userServiceImpl;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user = userServiceImpl.getUserByEmail(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        LOG.debug("User by email " + user);
//        UserRole userRole = user.getUserRole();
//        Set<SimpleGrantedAuthority> userAuthority = getUserAuthority(userRole);
//        return buildUserForAuthentication(user, userAuthority);
//    }
//
//    private UserDetails buildUserForAuthentication(User userByEmail, Set<SimpleGrantedAuthority> userAuthority) {
//        return new org.springframework.security.core.userdetails.User(userByEmail.getName(), userByEmail.getPassword(), true, true, true, true, userAuthority);
//
//    }
//
//    private Set<SimpleGrantedAuthority> getUserAuthority(UserRole userRole) {
//        return Collections.singleton(new SimpleGrantedAuthority(userRole.getUserRoleName()));
//    }
//}
