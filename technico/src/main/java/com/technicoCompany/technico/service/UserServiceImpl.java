package com.technicoCompany.technico.service;

import com.technicoCompany.technico.model.PropertyOwner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final List<PropertyOwner> owners = new ArrayList<>();

    @Override
    public PropertyOwner createUser(PropertyOwner propertyOwner) {

        for (PropertyOwner owner : owners) {
            //check for empty or null vat number given
            if (propertyOwner.getOwnerVatNumber() == null || propertyOwner.getOwnerVatNumber().isEmpty()) {
                throw new IllegalArgumentException("VAT number cannot be null or empty");
            }
            //check for user with same vatNumber
            if (owner.getOwnerVatNumber().equals(propertyOwner.getOwnerVatNumber())) {
                throw new IllegalArgumentException("A user with the VAT number '" + propertyOwner.getOwnerVatNumber() + "' already exists");
            }
        }
        owners.add(propertyOwner);
        return propertyOwner;
    }

    @Override
    public Optional<PropertyOwner> findUserByVatNumber(String vatNumber) {

        for (PropertyOwner owner : owners) {
            if (owner.getOwnerVatNumber().equals(vatNumber)) {
                return Optional.of(owner);
            }
        }
        return Optional.empty();
    }

    @Override
    public PropertyOwner updateUser(PropertyOwner updatedOwner) {
        // Find the existing owner by VAT number, or throw exception if not found
        PropertyOwner owner = findUserByVatNumber(updatedOwner.getOwnerVatNumber())
                .orElseThrow(() -> new IllegalArgumentException("User with VAT number " + updatedOwner.getOwnerVatNumber() + " not found"));

        //update only the fields that the user has changed and not everything
        if (updatedOwner.getOwnerName() != null) owner.setOwnerName(updatedOwner.getOwnerName());
        if (updatedOwner.getOwnerLastName() != null) owner.setOwnerLastName(updatedOwner.getOwnerLastName());
        if (updatedOwner.getOwnerAddress() != null) owner.setOwnerAddress(updatedOwner.getOwnerAddress());
        if (updatedOwner.getOwnerPhoneNumber() != null) owner.setOwnerPhoneNumber(updatedOwner.getOwnerPhoneNumber());
        if (updatedOwner.getOwnerEmail() != null) owner.setOwnerEmail(updatedOwner.getOwnerEmail());

        return owner;
    }

    @Override
    public boolean deleteUser(String vatNumber) {

        for (Iterator<PropertyOwner> iterator = owners.iterator(); iterator.hasNext(); ) {
            PropertyOwner owner = iterator.next();
            if (owner.getOwnerVatNumber().equals(vatNumber)) {
                iterator.remove();
                return true; // Deleted successfully
            }
        }
        return false; // No match found
    }





}