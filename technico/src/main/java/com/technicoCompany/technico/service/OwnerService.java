package com.technicoCompany.technico.service;

import com.technicoCompany.technico.model.Owner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OwnerService extends BaseServise<Owner, Long>{

    Owner createUser(Owner propertyOwner);

    Optional<Owner> findUserByVatNumber(String vatNumber);

    Owner updateUser(Owner updatedOwner);

    boolean deleteOwner(String vatNumber);

    Optional<Owner> findUserByEmail(String ownerEmail);

    public Set<Owner> findAllPropertyOwners();

}
