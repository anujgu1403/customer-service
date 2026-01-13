package com.retail.customer.application.services.impl;

import com.retail.customer.application.mapper.CustomerModelToCustomerMapper;
import com.retail.customer.application.mapper.CustomerToCustomerModelMapper;
import com.retail.customer.application.model.Customer;
import com.retail.customer.application.services.CustomerService;
import com.retail.customer.domain.CustomerModel;
import com.retail.customer.domain.exception.InvalidCredentialsException;
import com.retail.customer.infrastructure.repository.CustomerRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepositoryAdapter customerRepositoryAdapter;

    @Autowired
    private CustomerToCustomerModelMapper customerToCustomerModelMapper;

    @Autowired
    private CustomerModelToCustomerMapper customerModelToCustomerMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Customer register(Customer customer) {
        // Encode password before saving
        customer.setPassword(encoder.encode(customer.getPassword()));

        CustomerModel customerModel = customerToCustomerModelMapper.apply(customer);
        CustomerModel savedModel = customerRepositoryAdapter.register(customerModel);
        return customerModelToCustomerMapper.apply(savedModel);
    }

    @Override
    public boolean verifyPassword(Customer customer) {
        CustomerModel model = customerRepositoryAdapter.findByEmail(customer.getEmail());

        if (model == null) {
            throw new InvalidCredentialsException("User not found with email: " + customer.getEmail());
        }

        if (!encoder.matches(customer.getPassword(), model.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password");
        }

        return true;
    }

    @Override
    public CustomerModel findByEmail(String email) {
        return customerRepositoryAdapter.findByEmail(email);
    }
}
