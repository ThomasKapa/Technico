package com.technicoCompany.technico.service;

import com.technicoCompany.technico.exception.*;
import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl extends BaseServiceImpl<Owner> implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    protected JpaRepository<Owner, Long> getRepository() {
        return ownerRepository;
    }

    @Override
    public Owner createUser(Owner propertyOwner) {
        validateOwner(propertyOwner);

        // Check for duplicates using repository methods
        if (ownerRepository.existsByVatNumber(propertyOwner.getVatNumber())) {
            throw new UserAlreadyExistsException("A user with the VAT number '" + propertyOwner.getVatNumber() + "' already exists");
        }
        if (ownerRepository.existsByEmail(propertyOwner.getEmail())) {
            throw new UserAlreadyExistsException("A user with the email address '" + propertyOwner.getEmail() + "' already exists");
        }
        if (ownerRepository.existsByPhoneNumber(propertyOwner.getPhoneNumber())) {
            throw new UserAlreadyExistsException("A user with the phone number '" + propertyOwner.getPhoneNumber() + "' already exists");
        }

        return ownerRepository.save(propertyOwner);
    }

    private void validateOwner(Owner owner) {
        if (owner.getVatNumber() == null || owner.getVatNumber().isEmpty()) {
            throw new InvalidVatNumberException("VAT number cannot be null or empty");
        }

        if (owner.getEmail() == null || !owner.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            throw new InvalidEmailException(owner.getEmail() + " is not a valid email address");
        }

        if (owner.getPhoneNumber() == null || !owner.getPhoneNumber().matches("\\+?[0-9\\-\\(\\)\\s]*[0-9]+")) {
            throw new InvalidPhoneNumberException("Invalid phone number: " + owner.getPhoneNumber());
        }
    }

    @Override
    public Optional<Owner> findUserByVatNumber(String vatNumber) {
        return ownerRepository.findByVatNumber(vatNumber);
    }

    @Override
    public Owner updateUser(Owner updatedOwner) {
        Owner existingOwner = ownerRepository.findByVatNumber(updatedOwner.getVatNumber())
                .orElseThrow(() -> new InvalidVatNumberException("User with VAT number " + updatedOwner.getVatNumber() + " not found"));

        // Update only non-null fields
        if (updatedOwner.getFirstName() != null) existingOwner.setFirstName(updatedOwner.getFirstName());
        if (updatedOwner.getLastName() != null) existingOwner.setLastName(updatedOwner.getLastName());
        if (updatedOwner.getAddress() != null) existingOwner.setAddress(updatedOwner.getAddress());

        if (updatedOwner.getPhoneNumber() != null) {
            if (ownerRepository.existsByPhoneNumber(updatedOwner.getPhoneNumber())) {
                throw new UserAlreadyExistsException("Phone number '" + updatedOwner.getPhoneNumber() + "' already exists");
            }
            existingOwner.setPhoneNumber(updatedOwner.getPhoneNumber());
        }

        if (updatedOwner.getEmail() != null) {
            if (ownerRepository.existsByEmail(updatedOwner.getEmail())) {
                throw new UserAlreadyExistsException("Email '" + updatedOwner.getEmail() + "' already exists");
            }
            existingOwner.setEmail(updatedOwner.getEmail());
        }

        return ownerRepository.save(existingOwner);
    }

    @Override
    public boolean deleteOwnerByVatnumber(String vatNumber) {
        Owner owner = ownerRepository.findByVatNumber(vatNumber)
                .orElseThrow(() -> new InvalidVatNumberException("User with VAT number " + vatNumber + " not found"));

        ownerRepository.delete(owner);
        return true;
    }

    public boolean deleteOwnerByEmail(String email) {
        Owner owner = ownerRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidVatNumberException("User with email " + email + " not found"));

        ownerRepository.delete(owner);
        return true;
    }

    @Override
    public Optional<Owner> findUserByEmail(String ownerEmail) {
        return ownerRepository.findByEmail(ownerEmail);
    }

    @Override
    public Set<Owner> findAllPropertyOwners() {
        return new HashSet<>(ownerRepository.findAll());
    }


}
