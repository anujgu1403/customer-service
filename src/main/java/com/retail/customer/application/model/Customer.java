package com.retail.customer.application.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private String firstName;

    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
