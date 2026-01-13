// com.retail.customer.domain.exception/EmailAlreadyExistsException.java
package com.retail.customer.domain.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}