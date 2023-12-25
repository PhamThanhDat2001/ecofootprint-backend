package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.EcoFootprintLog;
import com.doan.ecofootprint_be.entity.EnergyConsumption;
import com.doan.ecofootprint_be.repository.EnergyConsumptionRepository;
import com.doan.ecofootprint_be.service.ILogService;
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
public class EnergyConsumptionController {
    @Autowired
    private EnergyConsumptionRepository energyConsumptionRepository;
    @GetMapping("/energyconsumption")
    public List<EnergyConsumption> findAllAcount(){
        return energyConsumptionRepository.findAll();
    }
    @PostMapping("/energyconsumption")
    ResponseEntity<?> create(@RequestBody EnergyConsumption energyConsumption) {

        EnergyConsumption created = energyConsumptionRepository.save(energyConsumption);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/energyconsumption/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
          energyConsumptionRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/energyconsumption/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody EnergyConsumption energyConsumption) {
        Optional<EnergyConsumption> existingLogOptional = energyConsumptionRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            EnergyConsumption existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setDate(energyConsumption.getDate());
            existingLog.setUnit(energyConsumption.getUnit());
            existingLog.setEnergyType(energyConsumption.getEnergyType());
            existingLog.setDescription(energyConsumption.getDescription());
            existingLog.setConsumption(energyConsumption.getConsumption());
            // Save the updated entity
            EnergyConsumption updatedLog = energyConsumptionRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
}
