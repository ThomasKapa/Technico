package com.technicoCompany.technico.service;

import com.technicoCompany.technico.model.Repair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RepairService extends BaseServise<Repair, Long> {
    Repair createRepair(Repair repair);

    Optional<Repair> findRepairById(Long repairId);

    List<Repair> findRepairsByOwnerVat(String vatNumber);

    List<Repair> findRepairsByDateRange(String startDate, String endDate);

    Repair updateRepair(Repair updatedRepair);

    boolean deleteRepairById(Long repairId);

    List<Repair> findRepairsByOwnerId(Long id);

    Optional<Repair> findOneRepairByOwnerId(Long id);

    Optional<Repair> findOneRepairByRangeOfDates(LocalDateTime startDate, LocalDateTime endDate);

    boolean deleteRepairByOwnerId(Long id);

    boolean deleteRepairsByDateRange(LocalDateTime start, LocalDateTime end);

    Repair  updateRepairWithOwner(Long ownerId, Repair repair);

    List<Repair> findRepairByPropertyId(Long id);
}
