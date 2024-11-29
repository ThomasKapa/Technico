package com.technicoCompany.technico.controller;

import com.technicoCompany.technico.model.Repair;
import com.technicoCompany.technico.service.RepairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/repairs")
public class RepairController {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
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

    @GetMapping("/owner/{vatNumber}")
    public ResponseEntity<List<Repair>> getRepairsByOwnerVat(@PathVariable String vatNumber) {
        List<Repair> repairs = repairService.findRepairsByOwnerVat(vatNumber);
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
