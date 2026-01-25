package com.retail.customer.infrastructure.repository;

import com.retail.customer.domain.model.CustomerModel;

public interface CustomerRepository {

    CustomerModel findByEmail(String email);

    CustomerModel register(CustomerModel customerModel);
}

