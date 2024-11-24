package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final OwnerService ownerService;

    //Αγνοήστε αυτή την έφτιαξα για έλεγχο
    @GetMapping("check")
     public ResponseEntity<String> testEndpoint() {    return ResponseEntity.ok("PropertyOwner endpoint is working!");}
    public UserController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<Owner> createUser(@RequestBody Owner propertyOwner) {
        return ResponseEntity.ok(ownerService.createUser(propertyOwner));
    }

    @GetMapping("{vatNumber}")
    public ResponseEntity<Owner> getUserByVat(@PathVariable String vatNumber) {
        Owner propertyOwner = ownerService.findUserByVatNumber(vatNumber).orElse(null);
        if (propertyOwner != null) {
            return ResponseEntity.ok(propertyOwner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //updated to return a message if user not found.
    @PutMapping("/{vatNumber}")
    public ResponseEntity<Owner> updateUser(@PathVariable String vatNumber, @RequestBody Owner propertyOwner) {
        boolean userExists = ownerService.findUserByVatNumber(vatNumber).isPresent();

        if (userExists) {
            propertyOwner.setVatNumber(vatNumber);
            return ResponseEntity.ok(ownerService.updateUser(propertyOwner));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //updated to return a message if user not found.
    @DeleteMapping("/{vatNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable String vatNumber) {
        boolean isDeleted = ownerService.deleteOwner(vatNumber);
        // If found and deleted
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        // If not found
        return ResponseEntity.notFound().build();
    }


}