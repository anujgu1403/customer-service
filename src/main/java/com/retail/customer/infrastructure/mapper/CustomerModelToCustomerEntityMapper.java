package com.retail.customer.infrastructure.mapper;

import com.retail.customer.domain.model.CustomerModel;
import com.retail.customer.infrastructure.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelToCustomerEntityMapper {

    @Autowired
    private BCryptPasswordEncoder encoder;

    public CustomerEntity apply(CustomerModel customerModel){

        return CustomerEntity.builder()
                .firstName(customerModel.getFirstName())
                .lastName(customerModel.getLastName())
                .email(customerModel.getEmail())
                .passwordHash(encoder.encode(customerModel.getPassword()))
                .build();
    }
}
