package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.enumeration.UserRole;
import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final OwnerService ownerService;

    public AuthController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Owner> adminOwner = ownerService.findAllPropertyOwners().stream()
                .filter(owner -> owner.getRole() == UserRole.ADMIN
                        && owner.getUserName().equals(loginRequest.getUsername())
                        && owner.getPassword().equals(loginRequest.getPassword()))
                .findFirst();

        if (adminOwner.isPresent()) {
            return ResponseEntity.ok(Map.of("role", "ADMIN"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
