package com.technicoCompany.technico.service;

import com.technicoCompany.technico.model.Property;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final List<Property> properties = new ArrayList<>();

    @Override
    public Property createProperty(Property property) {
        properties.add(property);
        return property;
    }

    @Override
    public Optional<Property> findPropertyById(Long propertyId) {
        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            if (property.getPropertyId().equals(propertyId)) {
                return Optional.of(property);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Property> findPropertiesByOwnerVat(String vatNumber) {
        List<Property> list = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            if (property.getPropertyOwner().getOwnerVatNumber().equals(vatNumber)) {
                list.add(property);
            }
        }
        return list;
    }

    @Override
    public Property updateProperty(Property updatedProperty) {
        Property existingProperty = findPropertyById(updatedProperty.getPropertyId()).orElse(null);
        if (existingProperty != null) {
            existingProperty.setPropertyAddress(updatedProperty.getPropertyAddress());
            existingProperty.setYearOfConstruction(updatedProperty.getYearOfConstruction());
            existingProperty.setPropertyType(updatedProperty.getPropertyType());
        }
        return existingProperty;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        for (Iterator<Property> iterator = properties.iterator(); iterator.hasNext(); ) {
            Property property = iterator.next();
            if (property.getPropertyId().equals(propertyId)) {
                iterator.remove();
                break;
            }
        }
    }













}
