package com.retail.customer.application.services;

import com.retail.customer.application.model.Customer;
import com.retail.customer.domain.model.CustomerModel;

public interface CustomerService {
    Customer register(Customer customer);

    boolean verifyPassword(Customer customer);

    CustomerModel findByEmail(String email);
}
