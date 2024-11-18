package com.technicoCompany.technico.exception;

public class InvalidVatNumberException extends RuntimeException {
    public InvalidVatNumberException(String message) {
        super(message);
    }
}