package com.retail.customer.application.mapper;

import com.retail.customer.application.model.Customer;
import com.retail.customer.domain.CustomerModel;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelToCustomerMapper {

    public Customer apply(CustomerModel customerModel){
        return Customer.builder()
                .lastName(customerModel.getFirstName())
                .firstName(customerModel.getLastName())
                .email(customerModel.getEmail())
                .password(customerModel.getPassword())
                .build();
    }
}
