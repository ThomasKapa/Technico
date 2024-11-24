package com.technicoCompany.technico.service;

import com.technicoCompany.technico.exception.InvalidIdException;

import com.technicoCompany.technico.model.Property;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl extends BaseServiceImpl<Property> implements PropertyService {

    private final List<Property> properties = new ArrayList<>();

    @Override
    public Property createProperty(Property property) {
        properties.add(property);
        return property;
    }

    @Override
    public Optional<Property> findPropertyById(Long propertyId) {
        for (Property property : properties) {
            if (property.getId().equals(propertyId)) {
                return Optional.of(property);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Property> findPropertiesByOwnerVat(String vatNumber) {
        List<Property> list = new ArrayList<>();
        for (Property property : properties) {
            if (property.getPropertyOwner().getVatNumber().equals(vatNumber)) {
                list.add(property);
            }
        }
        return list;
    }

    @Override
    public Property updateProperty(Property updatedProperty) {
        Property property = findPropertyById(updatedProperty.getId())
                .orElseThrow(() -> new InvalidIdException("Property with Id " + updatedProperty.getId() + " not found"));
        if (updatedProperty.getPropertyIdentificationE9Number() != null)
            property.setPropertyIdentificationE9Number(updatedProperty.getPropertyIdentificationE9Number());

        if (updatedProperty.getPropertyAddress() != null)
            property.setPropertyAddress(updatedProperty.getPropertyAddress());

        if (updatedProperty.getYearOfConstruction() != null)
            property.setYearOfConstruction(updatedProperty.getYearOfConstruction());

        if (updatedProperty.getPropertyType() != null)
            property.setPropertyType(updatedProperty.getPropertyType());

        return property;
    }

    @Override
    public boolean deleteProperty(Long propertyId) {
        for (Iterator<Property> iterator = properties.iterator(); iterator.hasNext(); ) {
            Property property = iterator.next();
            if (property.getId().equals(propertyId)) {
                iterator.remove();
                return true; // Deleted successfully
            }
        }
        return false; // No match found
    }


}
