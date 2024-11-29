package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.model.Owner;
import com.technicoCompany.technico.model.Repair;
import com.technicoCompany.technico.service.RepairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/repairs")
public class RepairController {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {

        this.repairService = repairService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable Long id) {
        repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }


}
