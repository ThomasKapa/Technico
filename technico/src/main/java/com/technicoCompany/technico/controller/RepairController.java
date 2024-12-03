package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.exception.ResourceNotFoundException;
import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.model.Property;
import com.technicoCompany.technico.model.Repair;
import com.technicoCompany.technico.service.OwnerService;
import com.technicoCompany.technico.service.RepairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/repairs")
public class RepairController {

    private final RepairService repairService;
    private final OwnerService ownerService;

    public RepairController(RepairService repairService, OwnerService ownerService) {

        this.repairService = repairService;
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<Repair>> getAllRepairs() {
        List<Repair> repairs = repairService.findAll();
        if (!repairs.isEmpty()) {
            return ResponseEntity.ok(repairs);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/new/repair")
    public ResponseEntity<Repair> createRepair(@RequestBody Repair repair) {
        Repair createdRepair = repairService.createRepair(repair);
        return ResponseEntity.created(URI.create("/repairs/" + createdRepair.getId()))
                .body(createdRepair);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Repair> getRepairById(@PathVariable Long id) {
        Repair repair = repairService.findRepairById(id).orElse(null);
        if (repair != null) {
            return ResponseEntity.ok(repair);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<List<Repair>> getRepairsByOwnerVat(@PathVariable Long id) {
        List<Repair> repairs = repairService.findRepairsByOwnerId(id);
        if (repairs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/rangeOfDates")
    public ResponseEntity<List<Repair>> getRepairsByRangeOfDates(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        List<Repair> repairs = repairService.findRepairsByDateRange(startDate, endDate);

        if (repairs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(repairs);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Repair> updateRepair(@PathVariable Long id, @RequestBody Repair repair) {
        repair.setId(id);
        return ResponseEntity.ok(repairService.updateRepair(repair));
    }

    @PutMapping("/owners/{id}")
    public ResponseEntity<Repair> updateRepairByOwnerId(@PathVariable Long id, @RequestBody Repair repair) {
        try {
            Repair updatedRepair = repairService.updateRepairWithOwner(id, repair);
            return ResponseEntity.ok(updatedRepair);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("/rangeOfDates")
    public ResponseEntity<Repair> updateRepairByRangeOfDates(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {

        Optional<Repair> repairOptional = repairService.findOneRepairByRangeOfDates(startDate, endDate);

        if (repairOptional.isPresent()) {
            Repair repair = repairOptional.get();
            Repair updatedRepair = repairService.updateRepair(repair);
            return ResponseEntity.ok(updatedRepair);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable Long id) {
        boolean isDeleted = repairService.deleteRepairById(id);
        //if found and deleted
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        //if not found
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/rangeOfDates")
    public ResponseEntity<Void> deleteRepairByrangeOfDates(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {

        boolean deleted = repairService.deleteRepairsByDateRange(startDate, endDate);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Void> deleteRepairByOwnerId(@PathVariable Long id, @RequestBody Repair repair) {
        boolean isDeleted = repairService.deleteRepairByOwnerId(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


