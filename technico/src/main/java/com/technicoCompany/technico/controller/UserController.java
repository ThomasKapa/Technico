package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.model.PropertyOwner;
import com.technicoCompany.technico.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<PropertyOwner> createUser(@RequestBody PropertyOwner propertyOwner) {
        return ResponseEntity.ok(userService.createUser(propertyOwner));
    }

    @GetMapping("/{vatNumber}")
    public ResponseEntity<PropertyOwner> getUserByVat(@PathVariable String vatNumber) {
        PropertyOwner propertyOwner = userService.findUserByVatNumber(vatNumber).orElse(null);
        if (propertyOwner != null) {
            return ResponseEntity.ok(propertyOwner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{vatNumber}")
    public ResponseEntity<PropertyOwner> updateUser(@PathVariable String vatNumber, @RequestBody PropertyOwner propertyOwner) {
        propertyOwner.setOwnerVatNumber(vatNumber);
        return ResponseEntity.ok(userService.updateUser(propertyOwner));
    }

    //updated to return a message if user not found.
    @DeleteMapping("/{vatNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable String vatNumber) {
        boolean isDeleted = userService.deleteUser(vatNumber);
        // If found and deleted
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        // If not found
        return ResponseEntity.notFound().build();
    }







}