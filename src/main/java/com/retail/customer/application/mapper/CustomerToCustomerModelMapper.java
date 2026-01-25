package com.retail.customer.application.mapper;

import com.retail.customer.application.model.Customer;
import com.retail.customer.domain.model.CustomerModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerModelMapper {

    public CustomerModel apply(Customer customer){
        return CustomerModel.builder()
                .lastName(customer.getFirstName())
                .firstName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .build();
    }
}
