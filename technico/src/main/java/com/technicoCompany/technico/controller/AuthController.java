package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final OwnerService ownerService;

    public AuthController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        Optional<Owner> adminOwner = ownerService.findAllPropertyOwners().stream()
//                .filter(owner -> owner.getUserName().equals(loginRequest.getUsername())
//                        && owner.getPassword().equals(loginRequest.getPassword()))
//                .findFirst();
//
//        if (adminOwner.isPresent()) {
//            if (adminOwner.get().getRole() == UserRole.ADMIN) {
//                return ResponseEntity.ok(Map.of("role", "ADMIN"));
//            } else if (adminOwner.get().getRole() == UserRole.PROPERTY_OWNER) {
//                return ResponseEntity.ok(Map.of("role", "OWNER"));
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Owner> adminOwner = ownerService.findAllPropertyOwners().stream()
                .filter(owner -> owner.getUserName().equals(loginRequest.getUsername())
                        && owner.getPassword().equals(loginRequest.getPassword()))
                .findFirst();

        if (adminOwner.isPresent()) {
            Owner owner = adminOwner.get();
            Map<String, Object> response = new HashMap<>();
            response.put("role", owner.getRole().name());
            response.put("vatNumber", owner.getVatNumber());
            response.put("id",owner.getId());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
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
