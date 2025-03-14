package com.bci.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailFormatException(InvalidEmailFormatException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(InvalidPasswordFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPasswordFormatException(InvalidPasswordFormatException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.status(404).body(response);
    }
    
    @ExceptionHandler(InvalidUUIDFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidUUIDFormatException(InvalidUUIDFormatException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
