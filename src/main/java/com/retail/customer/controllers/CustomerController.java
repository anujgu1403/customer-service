package com.retail.customer.controllers;

import com.retail.customer.application.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/customer")
public interface CustomerController {

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody Customer customer);

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Customer customer);
}
