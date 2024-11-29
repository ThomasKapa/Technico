package com.technicoCompany.technico.controller;

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

    @PostMapping
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

    @GetMapping("/owner/{id}")
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

    @PutMapping("/owner/{id}")
    public ResponseEntity<Repair> updateRepairByOwnerId(@PathVariable Long id, @RequestBody Repair repair) {
        Optional<Owner> ownerOptional = ownerService.findOwnerById(id);

        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();

            if (repair.getProperty() != null) {
                // Set the owner to the property of the repair
                repair.getProperty().setOwner(owner);
            } else {
                // If property is null, handle it (optional)
                return ResponseEntity.badRequest().build();
            }
            Repair updatedRepair = repairService.updateRepair(repair);
            return ResponseEntity.ok(updatedRepair);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/rangeOfDates")
    public ResponseEntity<Repair> updateRepairByRangeOfDates(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

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
        repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }
}

