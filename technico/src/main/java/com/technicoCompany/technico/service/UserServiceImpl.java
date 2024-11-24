package com.technicoCompany.technico.service;

import com.technicoCompany.technico.exception.InvalidEmailException;
import com.technicoCompany.technico.exception.InvalidPhoneNumberException;
import com.technicoCompany.technico.exception.InvalidVatNumberException;
import com.technicoCompany.technico.exception.UserAlreadyExistsException;
import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public UserServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    @Override
    public Owner createUser(Owner propertyOwner) {

        //check for empty or null vat number given
        if (propertyOwner.getVatNumber() == null || propertyOwner.getVatNumber().isEmpty()) {
            throw new InvalidVatNumberException("VAT number cannot be null or empty");
        }
        //        Email checking
        //        The following restrictions are imposed in the email address’ local part by using this regex:
        //        It allows numeric values from 0 to 9.
        //        Both uppercase and lowercase letters from a to z are allowed.
        //        Allowed are underscore “_”, hyphen “-“, and dot “.”
        //        Dot isn’t allowed at the start and end of the local part.+
        //        Consecutive dots aren’t allowed.
        //        For the local part, a maximum of 64 characters are allowed.
        //
        //        Restrictions for the domain part in this regular expression include:
        //        It allows numeric values from 0 to 9.
        //        We allow both uppercase and lowercase letters from a to z.
        //        Hyphen “-” and dot “.” aren’t allowed at the start and end of the domain part.
        //        No consecutive dots.
        if (propertyOwner.getEmail() == null || !propertyOwner.getEmail().matches
                ("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new InvalidEmailException(propertyOwner.getEmail() + "' is not a valid email address");
        }
        //phone check
        if (propertyOwner.getPhoneNumber() == null || !propertyOwner.getPhoneNumber().matches("\\+?[0-9\\-\\(\\)\\s]*[0-9]+")) {
            throw new InvalidPhoneNumberException("Invalid phone number: " + propertyOwner.getPhoneNumber());
        }
        for (Owner owner : ownerRepository.findAll()) {

            //check for user with same vatNumber
            if (ownerRepository.existsByVatNumber(propertyOwner.getVatNumber())) {
                throw new UserAlreadyExistsException("A user with the VAT number '" + propertyOwner.getVatNumber() + "' already exists");
            }
            //check for user with same email
            if (ownerRepository.existsByEmail(propertyOwner.getEmail())) {
                throw new UserAlreadyExistsException("A user with the email address '" + propertyOwner.getEmail() + "' already exists");
            }
            //check for user with same phone

            if (ownerRepository.existsByPhoneNumber(propertyOwner.getPhoneNumber())) {
                throw new UserAlreadyExistsException("A user with the phone number '" + propertyOwner.getPhoneNumber() + "' already exists");
            }

        }
        ownerRepository.save(propertyOwner);
        return propertyOwner;
    }


    @Override
    public Optional<Owner> findUserByVatNumber(String vatNumber) {
        return ownerRepository.findByVatNumber(vatNumber);
    }


    @Override
    public Owner updateUser(Owner updatedOwner) {
        // Find the existing owner by VAT number, or throw exception if not found
        Owner owner = ownerRepository.findByVatNumber(updatedOwner.getVatNumber())
                .orElseThrow(() -> new InvalidVatNumberException("User with VAT number " + updatedOwner.getVatNumber() + " not found"));

        //update only the fields that the user has changed and not everything
        if (updatedOwner.getFirstName() != null) owner.setFirstName(updatedOwner.getFirstName());
        if (updatedOwner.getLastName() != null) owner.setLastName(updatedOwner.getLastName());
        if (updatedOwner.getAddress() != null) owner.setAddress(updatedOwner.getAddress());
        if (updatedOwner.getPhoneNumber() != null) owner.setPhoneNumber(updatedOwner.getPhoneNumber());
        if (updatedOwner.getEmail() != null) owner.setEmail(updatedOwner.getEmail());

        ownerRepository.save(owner);
        return owner;
    }

    @Override
    public boolean deleteOwner(String vatNumber) {
        Owner owner = ownerRepository.findByVatNumber(vatNumber)
                .orElseThrow(() -> new InvalidVatNumberException("User with VAT number " + vatNumber + " not found"));

        ownerRepository.delete(owner);
        return true;
    }

    @Override
    public Optional<Owner> findUserByEmail(String ownerEmail) {
        return ownerRepository.findByOwnerEmail(ownerEmail);
    }

    @Override
    public Set<Owner> findAllPropertyOwners() {
        return new HashSet<>(ownerRepository.findAll());
    }


}