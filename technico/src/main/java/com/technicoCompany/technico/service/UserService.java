package com.technicoCompany.technico.service;

import com.technicoCompany.technico.model.PropertyOwner;

import java.util.Optional;

public interface UserService {
    PropertyOwner createUser(PropertyOwner propertyOwner);

    Optional<PropertyOwner> findUserByVatNumber(String vatNumber);

    Optional<PropertyOwner> updateUser(PropertyOwner updatedOwner);

    boolean deleteUser(String vatNumber);


}
