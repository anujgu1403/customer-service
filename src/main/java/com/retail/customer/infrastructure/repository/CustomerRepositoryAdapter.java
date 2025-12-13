package com.retail.customer.infrastructure.repository;

import com.retail.customer.domain.CustomerModel;
import com.retail.customer.infrastructure.entity.CustomerEntity;
import com.retail.customer.infrastructure.mapper.CustomerEntityToCustomerModelMapper;
import com.retail.customer.infrastructure.mapper.CustomerModelToCustomerEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerRepositoryAdapter implements CustomerRepository{

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    private CustomerEntityToCustomerModelMapper customerEntityToCustomerModelMapper;

    @Autowired
    private CustomerModelToCustomerEntityMapper customerModelToCustomerEntityMapper;

    @Override
    public CustomerModel findByEmail(String email) {
        CustomerEntity customerEntity = customerJpaRepository.findByEmail(email);
        return customerEntityToCustomerModelMapper.apply(customerEntity);
    }

    @Override
    public CustomerModel register(CustomerModel customerModel) {
        if (customerJpaRepository.existsByEmail(customerModel.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        CustomerEntity customerEntity = customerModelToCustomerEntityMapper.apply(customerModel);
        return customerEntityToCustomerModelMapper.apply(customerJpaRepository.save(customerEntity));
    }
}
