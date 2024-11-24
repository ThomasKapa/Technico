package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.model.Owner;
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
    public ResponseEntity<Owner> createUser(@RequestBody Owner propertyOwner) {
        return ResponseEntity.ok(userService.createUser(propertyOwner));
    }

    @GetMapping("{vatNumber}")
    public ResponseEntity<Owner> getUserByVat(@PathVariable String vatNumber) {
        Owner propertyOwner = userService.findUserByVatNumber(vatNumber).orElse(null);
        if (propertyOwner != null) {
            return ResponseEntity.ok(propertyOwner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //updated to return a message if user not found.
    @PutMapping("/{vatNumber}")
    public ResponseEntity<Owner> updateUser(@PathVariable String vatNumber, @RequestBody Owner propertyOwner) {
        boolean userExists = userService.findUserByVatNumber(vatNumber).isPresent();

        if (userExists) {
            propertyOwner.setVatNumber(vatNumber);
            return ResponseEntity.ok(userService.updateUser(propertyOwner));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //updated to return a message if user not found.
    @DeleteMapping("/{vatNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable String vatNumber) {
        boolean isDeleted = userService.deleteOwner(vatNumber);
        // If found and deleted
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        // If not found
        return ResponseEntity.notFound().build();
    }


}