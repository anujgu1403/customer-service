package com.retail.customer.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
