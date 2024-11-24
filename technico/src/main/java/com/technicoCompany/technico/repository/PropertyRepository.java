package com.technicoCompany.technico.repository;

import com.technicoCompany.technico.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends BaseRepository<Property, Long> {

    // βγαζει property με βαση το αφμ
    List<Property> findByOwnerVatNumber(String vatNumber);

    // βγαζει property με βαση το address
    Optional<Property> findByAddress(String address);

    // βγαζει oλα τα property του owner με βαση το αφμ
    List<Property> findByOwnerVatNumberOrderByAddress(String vatNumber);
}
