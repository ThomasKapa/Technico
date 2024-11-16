package com.technicoCompany.technico.service;

import com.technicoCompany.technico.exception.UserAlreadyExistsException;
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

        //check for empty or null vat number given
        if (propertyOwner.getOwnerVatNumber() == null || propertyOwner.getOwnerVatNumber().isEmpty()) {
            throw new IllegalArgumentException("VAT number cannot be null or empty");
        }
        //        Email checking
        //        The following restrictions are imposed in the email address’ local part by using this regex:
        //        It allows numeric values from 0 to 9.
        //        Both uppercase and lowercase letters from a to z are allowed.
        //        Allowed are underscore “_”, hyphen “-“, and dot “.”
        //        Dot isn’t allowed at the start and end of the local part.
        //        Consecutive dots aren’t allowed.
        //        For the local part, a maximum of 64 characters are allowed.
        //
        //        Restrictions for the domain part in this regular expression include:
        //        It allows numeric values from 0 to 9.
        //        We allow both uppercase and lowercase letters from a to z.
        //        Hyphen “-” and dot “.” aren’t allowed at the start and end of the domain part.
        //        No consecutive dots.
        if (propertyOwner.getOwnerEmail() == null || !propertyOwner.getOwnerEmail().matches
                ("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new IllegalArgumentException(propertyOwner.getOwnerEmail() + "' is not a valid email address");
        }
        //phone check
        if (propertyOwner.getOwnerPhoneNumber() == null || !propertyOwner.getOwnerPhoneNumber().matches("\\+?[0-9\\-\\(\\)\\s]*[0-9]+")) {
            throw new IllegalArgumentException("Invalid phone number: " + propertyOwner.getOwnerPhoneNumber());
        }
        for (PropertyOwner owner : owners) {

            //check for user with same vatNumber
            if (owner.getOwnerVatNumber().equals(propertyOwner.getOwnerVatNumber())) {
                throw new UserAlreadyExistsException("A user with the VAT number '" + propertyOwner.getOwnerVatNumber() + "' already exists");
            }
            //check for user with same email
            if (owner.getOwnerEmail().equals(propertyOwner.getOwnerEmail())) {
                throw new UserAlreadyExistsException("A user with the email address '" + propertyOwner.getOwnerEmail() + "' already exists");
            }
            //check for user with same phone
            if (owner.getOwnerPhoneNumber().equals(propertyOwner.getOwnerPhoneNumber())) {
                throw new UserAlreadyExistsException("A user with the phone number '" + propertyOwner.getOwnerPhoneNumber() + "' already exists");
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

    @Override
    public Optional<PropertyOwner> findUserByEmail(String ownerEmail) {
        for (PropertyOwner owner : owners) {
            if (owner.getOwnerEmail().equals(ownerEmail)) {
                return Optional.of(owner);
            }
        }
        return Optional.empty();
    }


}