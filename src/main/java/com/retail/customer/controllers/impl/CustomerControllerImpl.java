package com.retail.customer.controllers.impl;

import com.retail.customer.application.model.Customer;
import com.retail.customer.application.services.CustomerService;
import com.retail.customer.application.util.JwtUtil;
import com.retail.customer.controllers.CustomerController;
import com.retail.customer.domain.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerControllerImpl implements CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        Customer registered = customerService.register(customer);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Registered successfully");
        response.put("fullName", registered.getFirstName() + " " + registered.getLastName());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> login(@RequestBody Customer customer) {
        customerService.verifyPassword(customer);  // will throw exception if fails

        String token = jwtUtil.generateToken(customer.getEmail());

        return ResponseEntity.ok(AuthResponse.builder()
                .token(token)
                .build());
    }
}
