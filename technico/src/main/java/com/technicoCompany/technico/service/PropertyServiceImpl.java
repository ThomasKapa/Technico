package com.technicoCompany.technico.service;

import com.technicoCompany.technico.exception.InvalidIdException;
import com.technicoCompany.technico.model.Property;
import com.technicoCompany.technico.repository.BaseRepository;
import com.technicoCompany.technico.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl extends BaseServiceImpl<Property> implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    protected BaseRepository<Property, Long> getRepository() {
        return propertyRepository;
    }

    @Override
    public Property createProperty(Property property) {
        propertyRepository.create(property);
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
        if (updatedProperty.getPropertyAddress() != null)
            property.setPropertyAddress(updatedProperty.getPropertyAddress());
        if (updatedProperty.getYearOfConstruction() != null)
            property.setYearOfConstruction(updatedProperty.getYearOfConstruction());
        if (updatedProperty.getPropertyType() != null)
            property.setPropertyType(updatedProperty.getPropertyType());

        propertyRepository.create(property);
        return property;
    }

    @Override
    public boolean deleteProperty(Long propertyId) {
        Optional<Property> property = propertyRepository.findById(propertyId);
        if (property.isPresent()) {
            propertyRepository.delete(property.get());
            return true;
        }
        return false;
    }


}
