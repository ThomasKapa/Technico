package com.technicoCompany.technico.repository;

import com.technicoCompany.technico.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>{
    // βγαζει τον owner με βαση το email
    Optional<Owner> findByEmail(String ownerEmail);

    boolean existsByVatNumber(String vatNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Owner> findByVatNumber(String vatNumber);


    Optional<Owner> findOwnerById(Long id);
}
