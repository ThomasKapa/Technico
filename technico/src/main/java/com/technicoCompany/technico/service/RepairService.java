package com.technicoCompany.technico.service;

import com.technicoCompany.technico.model.Repair;

import java.util.List;
import java.util.Optional;

public interface RepairService {
    Repair createRepair(Repair repair);

    Optional<Repair> findRepairById(Long repairId);

    List<Repair> findRepairsByOwnerVat(String vatNumber);

    List<Repair> findRepairsByDateRange(String startDate, String endDate);

    Repair updateRepair(Repair updatedRepair);

    void deleteRepair(Long repairId);
}
