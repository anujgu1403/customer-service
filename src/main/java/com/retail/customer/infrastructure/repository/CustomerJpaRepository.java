package com.retail.customer.infrastructure.repository;

import com.retail.customer.infrastructure.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);

    boolean existsByEmail(String email);
}