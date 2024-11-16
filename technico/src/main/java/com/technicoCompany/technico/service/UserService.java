package com.technicoCompany.technico.service;

import com.technicoCompany.technico.model.PropertyOwner;

import java.util.Optional;

public interface UserService {
    PropertyOwner createUser(PropertyOwner propertyOwner);

    Optional<PropertyOwner> findUserByVatNumber(String vatNumber);

    PropertyOwner updateUser(PropertyOwner updatedOwner);

    void deleteUser(String vatNumber);


}
