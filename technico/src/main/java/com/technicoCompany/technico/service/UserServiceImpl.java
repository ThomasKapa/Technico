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
        owners.add(propertyOwner);
        return propertyOwner;
    }

    @Override
    public Optional<PropertyOwner> findUserByVatNumber(String vatNumber) {
        for (int i = 0; i < owners.size(); i++) {
            PropertyOwner owner = owners.get(i);
            if (owner.getOwnerVatNumber().equals(vatNumber)) {
                return Optional.of(owner);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<PropertyOwner> updateUser(PropertyOwner updatedOwner) {
        Optional<PropertyOwner> existingOwnerOptional = findUserByVatNumber(updatedOwner.getOwnerVatNumber());
        if (existingOwnerOptional.isPresent()) {
            PropertyOwner owner = existingOwnerOptional.get();
            if (updatedOwner.getOwnerName() != null) owner.setOwnerName(updatedOwner.getOwnerName());
            if (updatedOwner.getOwnerLastName() != null) owner.setOwnerLastName(updatedOwner.getOwnerLastName());
            if (updatedOwner.getOwnerAddress() != null) owner.setOwnerAddress(updatedOwner.getOwnerAddress());
            if (updatedOwner.getOwnerPhoneNumber() != null) owner.setOwnerPhoneNumber(updatedOwner.getOwnerPhoneNumber());
            if (updatedOwner.getOwnerEmail() != null) owner.setOwnerEmail(updatedOwner.getOwnerEmail());
            return Optional.of(owner);
        } else {
            return Optional.empty();
        }
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