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
    public PropertyOwner updateUser(PropertyOwner updatedOwner) {
        Optional<PropertyOwner> existingOwnerOptional = findUserByVatNumber(updatedOwner.getOwnerVatNumber());
        if (existingOwnerOptional.isPresent()) {
            PropertyOwner owner = existingOwnerOptional.get();
            owner.setOwnerName(updatedOwner.getOwnerName());
            owner.setOwnerLastName(updatedOwner.getOwnerLastName());
            owner.setOwnerAddress(updatedOwner.getOwnerAddress());
            owner.setOwnerPhoneNumber(updatedOwner.getOwnerPhoneNumber());
            owner.setOwnerEmail(updatedOwner.getOwnerEmail());
            return owner;
        } else {
            return null;
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