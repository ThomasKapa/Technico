package com.technicoCompany.technico.repository;

import com.technicoCompany.technico.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
    // βγαζει τα repairs με βαση το αφμ
    @Query("SELECT r FROM Repair r JOIN r.property p WHERE p.owner.vatNumber = :vatNumber")
    List<Repair> findByOwner_VatNumber(@Param("vatNumber") String vatNumber);

    @Query("SELECT r FROM Repair r JOIN r.property p WHERE p.owner.id = :propertyOwnerId")
    List<Repair> findRepairsByPropertyOwnerId(@Param("propertyOwnerId") Long propertyOwnerId);

    // βγαζει τα repairs με βαση το ευρος των ημερομηνιων
    List<Repair> findByScheduledRepairDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<Repair> findOneRepairByOwnerId(Long id);

    @Query("SELECT r FROM Repair r WHERE r.scheduledRepairDate BETWEEN :startDate AND :endDate")
    Optional<Repair> findOneRepairByRangeOfDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
