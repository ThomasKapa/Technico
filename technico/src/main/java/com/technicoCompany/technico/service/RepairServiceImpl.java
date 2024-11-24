package com.technicoCompany.technico.service;

import com.technicoCompany.technico.exception.ResourceNotFoundException;
import com.technicoCompany.technico.model.Repair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RepairServiceImpl implements RepairService {

    private final List<Repair> repairs = new ArrayList<>();

    @Override
    public Repair createRepair(Repair repair) {
        repairs.add(repair);
        return repair;
    }

    @Override
    public Optional<Repair> findRepairById(Long repairId) {
        for (Repair repair : repairs){
            if (repair.getId() == repairId) {
                return Optional.of(repair);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Repair> findRepairsByOwnerVat(String vatNumber) {
        List<Repair> list = new ArrayList<>();
        for (Repair repair : repairs){
            if (repair.getProperty().getPropertyOwner().getVatNumber().equals(vatNumber)) {
                list.add(repair);
            }
        }
        return list;
    }

    @Override
    public List<Repair> findRepairsByDateRange(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<Repair> list = new ArrayList<>();
        for (Repair repair : repairs){
            if (repair.getScheduledRepairDate().isAfter(start) && repair.getScheduledRepairDate().isBefore(end)) {
                list.add(repair);
            }
        }
        return list;
    }

    @Override
    public Repair updateRepair(Repair updatedRepair) {
        Optional<Repair> existingRepairOptional = findRepairById(updatedRepair.getId());
        if (existingRepairOptional.isEmpty()) {
            throw new ResourceNotFoundException("Repair not found with ID: " + updatedRepair.getId());
        }
        Repair existingRepair = existingRepairOptional.get();
        existingRepair.setRepairStatus(updatedRepair.getRepairStatus());
        existingRepair.setRepairType(updatedRepair.getRepairType());
        existingRepair.setRepairCost(updatedRepair.getRepairCost());
        existingRepair.setRepairAddress(updatedRepair.getRepairAddress());
        existingRepair.setWorkToBeDone(updatedRepair.getWorkToBeDone());

        return existingRepair;
    }

    @Override
    public boolean deleteRepair(Long repairId) {
        for (Iterator<Repair> iterator = repairs.iterator(); iterator.hasNext(); ) {
            Repair repair = iterator.next();
            if (repair.getId() ==  repairId) {
                iterator.remove();
                return true; // Deleted successfully
            }
        }
        return false; // No match found
    }








}