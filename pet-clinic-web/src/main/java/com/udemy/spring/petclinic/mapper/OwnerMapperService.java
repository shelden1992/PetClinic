//package com.udemy.spring.petclinic.mapper;
//
//import com.udemy.spring.petclinic.form.RegistrationForm;
//import com.udemy.spring.petclinic.model.Owner;
//import ma.glasnost.orika.MapperFactory;
//import ma.glasnost.orika.impl.ConfigurableMapper;
//import org.springframework.stereotype.Service;
//
///**
// * Created by Shelupets Denys on 17.07.2020.
// */
//@Service
//public class OwnerMapperService extends ConfigurableMapper {
//    @Override
//    protected void configure(MapperFactory factory) {
//        factory.classMap(RegistrationForm.class, Owner.class)
//                .mapNulls(false).mapNullsInReverse(false)
//                .exclude("specialities")
//                .exclude("workProcessing")
//                .exclude("userRole")
//                .byDefault()
//                .register();
//    }
//}
