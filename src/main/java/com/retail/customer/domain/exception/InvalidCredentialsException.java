// com.retail.customer.domain.exception/InvalidCredentialsException.java
package com.retail.customer.domain.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}