package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.model.Property;
import com.technicoCompany.technico.service.OwnerService;
import com.technicoCompany.technico.service.OwnerServiceImpl;
import com.technicoCompany.technico.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final OwnerService ownerService;

    public PropertyController(PropertyService propertyService, OwnerService ownerService) {
        this.propertyService = propertyService;
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.findAll();

        if (!properties.isEmpty()) {
            return ResponseEntity.ok(properties);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property createdProperty = propertyService.createProperty(property);
        return ResponseEntity.created(URI.create("/properties/" + createdProperty.getId()))
                .body(createdProperty);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.findPropertyById(id).orElse(null);
        if (property != null) {
            return ResponseEntity.ok(property);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/owner/{vatNumber}")
    public ResponseEntity<List<Property>> getPropertiesByOwnerVat(@PathVariable String vatNumber) {
        List<Property> properties = propertyService.findPropertiesByOwnerVat(vatNumber);

        if (properties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(properties);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updatePropertyById(@PathVariable Long id, @RequestBody Property property) {
        Optional<Property> existingProperty = propertyService.findPropertyById(id);

        if (existingProperty.isPresent()) {
            property.setId(id);
            Property updatedProperty = propertyService.updateProperty(property);
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/owner/{vatNumber}")
    public ResponseEntity<Property> updatePropertyByVatnumber(@PathVariable String vatNumber, @RequestBody Property property) {
        // Retrieve the owner by VAT number
        Optional<Owner> ownerOptional = ownerService.findUserByVatNumber(vatNumber);

        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            property.setOwner(owner);
            Property updatedProperty = propertyService.updateProperty(property);
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyById(@PathVariable Long id) {
        boolean isDeleted = propertyService.deletePropertyById(id);
        //if found and deleted
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        //if not found
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyByOwnerVat(@PathVariable String vatNumber) {
        boolean isDeleted = propertyService.deletePropertyByOwnerVat(vatNumber);
        //if found and deleted
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        //if not found
        return ResponseEntity.notFound().build();
    }

}
