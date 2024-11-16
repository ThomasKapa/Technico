package com.technicoCompany.technico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionHandler {

    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException re) {
        return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
