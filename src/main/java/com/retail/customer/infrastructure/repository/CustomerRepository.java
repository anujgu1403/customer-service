package com.retail.customer.infrastructure.repository;

import com.retail.customer.domain.CustomerModel;
import com.retail.customer.infrastructure.entity.CustomerEntity;

public interface CustomerRepository {

    CustomerModel findByEmail(String email);

    CustomerModel register(CustomerModel customerModel);
}

