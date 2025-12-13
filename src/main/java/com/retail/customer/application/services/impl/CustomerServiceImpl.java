package com.retail.customer.application.services.impl;

import com.retail.customer.application.mapper.CustomerModelToCustomerMapper;
import com.retail.customer.application.mapper.CustomerToCustomerModelMapper;
import com.retail.customer.application.model.Customer;
import com.retail.customer.application.services.CustomerService;
import com.retail.customer.domain.CustomerModel;
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
        CustomerModel customerModel = customerRepositoryAdapter.register(customerToCustomerModelMapper.apply(customer));
        return customerModelToCustomerMapper.apply(customerModel);
    }

    public boolean verifyPassword(Customer customer) {
        CustomerModel customerModel = findByEmail(customer.getEmail());
        return customerModel != null && encoder.matches(customer.getPassword(), customerModel.getPassword());
    }

    @Override
    public CustomerModel findByEmail(String email) {
        return customerRepositoryAdapter.findByEmail(email);
    }
}
