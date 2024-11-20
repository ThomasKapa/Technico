package com.technicoCompany.technico.repository;

import com.technicoCompany.technico.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {
    // βγαζει τον owner με βαση το email
    Optional<Owner> findByOwnerEmail(String ownerEmail);

}