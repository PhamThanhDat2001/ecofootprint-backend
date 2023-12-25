package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.FoodConsumption;
import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import com.doan.ecofootprint_be.repository.FoodConsumptionRepository;
import com.doan.ecofootprint_be.repository.GreenEnergyUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1")
@Validated
public class GreenEnergyUsageController {
    @Autowired
    private GreenEnergyUsageRepository greenEnergyUsageRepository;
    @GetMapping("/greenenergyusage")
    public List<GreenEnergyUsage> findAllAcount(){
        return greenEnergyUsageRepository.findAll();
    }
    @PostMapping("/greenenergyusage")
    ResponseEntity<?> create(@RequestBody GreenEnergyUsage greenEnergyUsage) {

        GreenEnergyUsage created = greenEnergyUsageRepository.save(greenEnergyUsage);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/greenenergyusage/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        greenEnergyUsageRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/greenenergyusage/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody GreenEnergyUsage greenEnergyUsage) {
        Optional<GreenEnergyUsage> existingLogOptional = greenEnergyUsageRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            GreenEnergyUsage existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setDate(greenEnergyUsage.getDate());
            existingLog.setUnit(greenEnergyUsage.getUnit());
            existingLog.setEnergySource(greenEnergyUsage.getEnergySource());
            existingLog.setDescription(greenEnergyUsage.getDescription());
            existingLog.setUsageAmount(greenEnergyUsage.getUsageAmount());
            // Save the updated entity
            GreenEnergyUsage updatedLog = greenEnergyUsageRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
}
