package com.technicoCompany.technico.service;

import com.technicoCompany.technico.exception.ResourceNotFoundException;
import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.model.Repair;
import com.technicoCompany.technico.repository.RepairRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepairServiceImpl extends BaseServiceImpl<Repair> implements RepairService {

    private final RepairRepository repairRepository;

    private final OwnerService ownerService;

    @Override
    protected JpaRepository<Repair, Long> getRepository() {
        return repairRepository;
    }

    @Override
    public Repair createRepair(Repair repair) {
        repairRepository.save(repair);
        return repair;
    }

    @Override
    public Optional<Repair> findRepairById(Long repairId) {
        return repairRepository.findById(repairId);
    }

    @Override
    public List<Repair> findRepairsByOwnerVat(String vatNumber) {
        return repairRepository.findByOwner_VatNumber(vatNumber);
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

        repairRepository.save(repair);
        return repair;
    }


    @Override
    public boolean deleteRepairById(Long repairId) {
        Optional<Repair> repair = repairRepository.findById(repairId);
        if (repair.isPresent()) {
            repairRepository.delete(repair.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Repair> findRepairsByOwnerId(Long id) {
        return repairRepository.findRepairsByPropertyOwnerId(id);
    }

    @Override
    public Optional<Repair> findOneRepairByOwnerId(Long id) {
        return repairRepository.findOneRepairByOwnerId(id);
    }

    @Override
    public Optional<Repair> findOneRepairByRangeOfDates(LocalDateTime startDate, LocalDateTime endDate) {
        return repairRepository.findOneRepairByRangeOfDates(startDate, endDate);
    }

    @Override
    public boolean deleteRepairByOwnerId(Long id) {
        Optional<Repair> repair = repairRepository.findOneRepairByOwnerId(id);
        if (repair.isPresent()) {
            repairRepository.delete(repair.get());
            return true;
        }
        return false;

    }

    @Override
    public boolean deleteRepairsByDateRange(LocalDateTime start, LocalDateTime end) {
        Optional<Repair> repair = repairRepository.findOneRepairByRangeOfDates(start, end);
        if (repair.isPresent()) {
            repairRepository.delete(repair.get());
            return true;
        }
        return false;
    }

    @Transactional
    public Repair updateRepairWithOwner(Long ownerId, Repair repair) {
        Owner owner = ownerService.findOwnerById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));
        if (repair.getProperty() == null) {
            throw new IllegalArgumentException("Property cannot be null");
        }
        repair.getProperty().setOwner(owner);
        return updateRepair(repair);
    }

    @Override
    public List<Repair> findRepairByPropertyId(Long id) {
        return repairRepository.findRepairsByPropertyId(id);
    }

}

