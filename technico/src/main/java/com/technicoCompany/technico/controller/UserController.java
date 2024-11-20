package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    //Αγνοήστε αυτή την έφτιαξα για έλεγχο
    @GetMapping("check")
     public ResponseEntity<String> testEndpoint() {    return ResponseEntity.ok("PropertyOwner endpoint is working!");}
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<PropertyOwner> createUser(@RequestBody PropertyOwner propertyOwner) {
        return ResponseEntity.ok(userService.createUser(propertyOwner));
    }

    @GetMapping("{vatNumber}")
    public ResponseEntity<PropertyOwner> getUserByVat(@PathVariable String vatNumber) {
        PropertyOwner propertyOwner = userService.findUserByVatNumber(vatNumber).orElse(null);
        if (propertyOwner != null) {
            return ResponseEntity.ok(propertyOwner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //updated to return a message if user not found.
    @PutMapping("/{vatNumber}")
    public ResponseEntity<PropertyOwner> updateUser(@PathVariable String vatNumber, @RequestBody PropertyOwner propertyOwner) {
        boolean userExists = userService.findUserByVatNumber(vatNumber).isPresent();

        if (userExists) {
            propertyOwner.setOwnerVatNumber(vatNumber);
            return ResponseEntity.ok(userService.updateUser(propertyOwner));
        } else {
            return ResponseEntity.notFound().build();
        }
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