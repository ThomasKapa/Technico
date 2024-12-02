package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final OwnerService ownerService;

    public UserController(OwnerService ownerService) {

        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<Set<Owner>> getAllOwners() {
        Set<Owner> owners = ownerService.findAllPropertyOwners();
        if (!owners.isEmpty()) {
            return ResponseEntity.ok(owners);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/emails/{email}")
    public ResponseEntity<Owner> getUserByEmail(@PathVariable String email) {
        Owner propertyOwner = ownerService.findUserByEmail(email).orElse(null);
        if (propertyOwner != null) {
            return ResponseEntity.ok(propertyOwner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Owner> createUser(@RequestBody Owner propertyOwner) {
        Owner createdOwner = ownerService.createUser(propertyOwner);
        return ResponseEntity.created(URI.create("/users/" + createdOwner.getId()))
                .body(createdOwner);
    }


    @GetMapping("/vat/{vatNumber}")
    public ResponseEntity<Owner> getUserByVat(@PathVariable String vatNumber) {
        Owner propertyOwner = ownerService.findUserByVatNumber(vatNumber).orElse(null);
        if (propertyOwner != null) {
            return ResponseEntity.ok(propertyOwner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //updated to return a message if user not found.
    @PutMapping("/user/update-by-vatNumber/{vatNumber}")
    public ResponseEntity<Owner> updateUserByVatnumber(@PathVariable String vatNumber, @RequestBody Owner propertyOwner) {
        boolean userExists = ownerService.findUserByVatNumber(vatNumber).isPresent();

        if (userExists) {
            propertyOwner.setVatNumber(vatNumber);
            return ResponseEntity.ok(ownerService.updateUser(propertyOwner));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/update-by-email/{email}")
    public ResponseEntity<Owner> updateUserByEmail(@PathVariable String email, @RequestBody Owner propertyOwner) {
        boolean userExists = ownerService.findUserByEmail(email).isPresent();

        if (userExists) {
            propertyOwner.setEmail(email);
            return ResponseEntity.ok(ownerService.updateUser(propertyOwner));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //updated to return a message if user not found.
    @DeleteMapping("/user/delete-by-vat/{vatNumber}")
    public ResponseEntity<Void> deleteUserByVat(@PathVariable String vatNumber) {
        boolean isDeleted = ownerService.deleteOwnerByVatnumber(vatNumber);
        // If found and deleted
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        // If not found
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/delete-by-email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        boolean isDeleted = ownerService.deleteOwnerByEmail(email);
        // If found and deleted
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        // If not found
        return ResponseEntity.notFound().build();
    }
}