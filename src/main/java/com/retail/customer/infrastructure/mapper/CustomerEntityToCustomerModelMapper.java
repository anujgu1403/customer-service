package com.retail.customer.infrastructure.mapper;

import com.retail.customer.domain.model.CustomerModel;
import com.retail.customer.infrastructure.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityToCustomerModelMapper {
    public CustomerModel apply(CustomerEntity customerEntity){
        return CustomerModel.builder()
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .email(customerEntity.getEmail())
                .password(customerEntity.getPasswordHash())
                .build();

    }
}
