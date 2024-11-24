package com.technicoCompany.technico.service;

import com.technicoCompany.technico.exception.ResourceNotFoundException;
import com.technicoCompany.technico.model.Repair;
import com.technicoCompany.technico.repository.BaseRepository;
import com.technicoCompany.technico.repository.RepairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepairServiceImpl extends BaseServiceImpl<Repair> implements RepairService {

    private final RepairRepository repairRepository;

    @Override
    protected BaseRepository<Repair, Long> getRepository() {
        return repairRepository;
    }
    @Override
    public Repair createRepair(Repair repair) {
        repairRepository.create(repair);
        return repair;
    }

    @Override
    public Optional<Repair> findRepairById(Long repairId) {
        return repairRepository.findById(repairId);
    }

    @Override
    public List<Repair> findRepairsByOwnerVat(String vatNumber) {
        return repairRepository.findByOwnerVatNumber(vatNumber);
    }

    @Override
    public List<Repair> findRepairsByDateRange(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return repairRepository.findByScheduledRepairDateBetween(start, end);
    }


    @Override
    public Repair updateRepair(Repair updatedRepair) {
        Repair repair = repairRepository.findById(updatedRepair.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Repair not found with ID: " + updatedRepair.getId()));

        // Update only non-null fields
        if (updatedRepair.getRepairStatus() != null) repair.setRepairStatus(updatedRepair.getRepairStatus());
        if (updatedRepair.getRepairType() != null) repair.setRepairType(updatedRepair.getRepairType());
        if (updatedRepair.getRepairCost() != null) repair.setRepairCost(updatedRepair.getRepairCost());
        if (updatedRepair.getRepairAddress() != null) repair.setRepairAddress(updatedRepair.getRepairAddress());
        if (updatedRepair.getWorkToBeDone() != null) repair.setWorkToBeDone(updatedRepair.getWorkToBeDone());

        repairRepository.create(repair);
        return repair;
    }


    @Override
    public boolean deleteRepair(Long repairId) {
        Optional<Repair> repair = repairRepository.findById(repairId);
        if (repair.isPresent()) {
            repairRepository.delete(repair.get());
            return true;
        }
        return false;
    }


}