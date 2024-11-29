package com.technicoCompany.technico.service;

import com.technicoCompany.technico.exception.InvalidIdException;
import com.technicoCompany.technico.model.Property;
import com.technicoCompany.technico.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl extends BaseServiceImpl<Property> implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    protected JpaRepository<Property, Long> getRepository() {
        return propertyRepository;
    }

    @Override
    public Property createProperty(Property property) {
        propertyRepository.save(property);
        return property;
    }

    public Optional<Property> findPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId);
    }
    @Override
    public List<Property> findPropertiesByOwnerVat(String vatNumber) {
        return propertyRepository.findByOwnerVatNumber(vatNumber);
    }

    @Override
    public Property updateProperty(Property updatedProperty) {
        Property property = propertyRepository.findById(updatedProperty.getId())
                .orElseThrow(() -> new InvalidIdException("Property with Id " + updatedProperty.getId() + " not found"));


        if (updatedProperty.getPropertyIdentificationE9Number() != null)
            property.setPropertyIdentificationE9Number(updatedProperty.getPropertyIdentificationE9Number());
        if (updatedProperty.getAddress() != null)
            property.setAddress(updatedProperty.getAddress());
        if (updatedProperty.getYearOfConstruction() != null)
            property.setYearOfConstruction(updatedProperty.getYearOfConstruction());
        if (updatedProperty.getPropertyType() != null)
            property.setPropertyType(updatedProperty.getPropertyType());

        propertyRepository.save(property);
        return property;
    }

    @Override
    public boolean deletePropertyById(Long propertyId) {
        Optional<Property> property = propertyRepository.findById(propertyId);
        if (property.isPresent()) {
            propertyRepository.delete(property.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePropertyByOwnerVat(String vatNumber) {
        Optional<Property> property = propertyRepository.findPropertyByOwnerVatNumber(vatNumber);
        if (property.isPresent()) {
            propertyRepository.delete(property.get());
            return true;
        }
        return false;
    }


}
