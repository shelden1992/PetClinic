//package com.udemy.spring.petclinic.mapper;
//
//import com.udemy.spring.petclinic.form.RegistrationForm;
//import com.udemy.spring.petclinic.model.Vet;
//import ma.glasnost.orika.MapperFactory;
//import ma.glasnost.orika.impl.ConfigurableMapper;
//import org.springframework.stereotype.Service;
//
///**
// * Created by Shelupets Denys on 17.07.2020.
// */
//@Service
//public class VetMapperService extends ConfigurableMapper {
//    @Override
//    protected void configure(MapperFactory factory) {
//        factory.classMap(RegistrationForm.class, Vet.class)
//                .mapNulls(false).mapNullsInReverse(false)
//                .exclude("pets")
//                .exclude("userRole")
//                .byDefault()
//                .register();
//    }
//}