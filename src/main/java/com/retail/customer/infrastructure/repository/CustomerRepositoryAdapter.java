package com.retail.customer.infrastructure.repository;

import com.retail.core.common.exception.ValidationException;
import com.retail.core.common.model.AlertModel;
import com.retail.customer.domain.model.CustomerModel;
import com.retail.customer.infrastructure.entity.CustomerEntity;
import com.retail.customer.infrastructure.mapper.CustomerEntityToCustomerModelMapper;
import com.retail.customer.infrastructure.mapper.CustomerModelToCustomerEntityMapper;
import com.retail.customer.infrastructure.validation.CustomerDataResponseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerRepositoryAdapter implements CustomerRepository{

    @Autowired
    private CustomerJpaRepository customerJpaRepository;

    @Autowired
    private CustomerEntityToCustomerModelMapper customerEntityToCustomerModelMapper;

    @Autowired
    private CustomerModelToCustomerEntityMapper customerModelToCustomerEntityMapper;

    @Autowired
    private CustomerDataResponseValidator customerDataResponseValidator;

    @Override
    public CustomerModel findByEmail(String email) {
        CustomerEntity customerEntity = customerJpaRepository.findByEmail(email);

        List<AlertModel> alerts = customerDataResponseValidator.apply(customerEntity);
        if (!alerts.isEmpty())
            throw new ValidationException(alerts);
        return customerEntityToCustomerModelMapper.apply(customerEntity);

    }

    @Override
    public CustomerModel register(CustomerModel customerModel) {
        if (customerJpaRepository.existsByEmail(customerModel.getEmail())) {
            CustomerEntity customerEntity= CustomerEntity.builder()
                    .userExists(true)
                    .build();
           throw new ValidationException(customerDataResponseValidator.apply(customerEntity));
        }
        CustomerEntity customerEntity = customerModelToCustomerEntityMapper.apply(customerModel);
        return customerEntityToCustomerModelMapper.apply(customerJpaRepository.save(customerEntity));
    }
}
