package com.technicoCompany.technico.repository;

import com.technicoCompany.technico.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
    // βγαζει τα repairs με βαση το αφμ
    List<Repair> findByPropertyOwner_OwnerVatNumber(String vatNumber);

    // βγαζει τα repairs με βαση το ευρος των ημερομηνιων
    List<Repair> findByScheduledRepairDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}