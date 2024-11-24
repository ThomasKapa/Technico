package com.technicoCompany.technico.service;

import com.technicoCompany.technico.model.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    Property createProperty(Property property);

    Optional<Property> findPropertyById(Long propertyId);

    List<Property> findPropertiesByOwnerVat(String vatNumber);

    Property updateProperty(Property updatedProperty);

    boolean deleteProperty(Long propertyId);
}
