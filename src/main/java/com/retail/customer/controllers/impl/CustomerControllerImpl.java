package com.retail.customer.controllers.impl;

import com.retail.customer.application.model.Customer;
import com.retail.customer.application.services.CustomerService;
import com.retail.customer.application.util.JwtUtil;
import com.retail.customer.controllers.CustomerController;
import com.retail.customer.domain.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerControllerImpl implements CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> register(Customer customer) {
        Customer registeredCustomer = customerService.register(customer);
        return ResponseEntity.ok(registeredCustomer.getFirstName() + " " + registeredCustomer.getLastName() + " Registered successfully");
    }

    @Override
    public ResponseEntity<?> login(Customer customer) {
        if (!customerService.verifyPassword(customer))
            return ResponseEntity.status(401).body("Invalid credentials");
        else
            return ResponseEntity.ok(AuthResponse.builder()
                    .token(jwtUtil.generateToken(customer.getEmail())).build());
    }
}
