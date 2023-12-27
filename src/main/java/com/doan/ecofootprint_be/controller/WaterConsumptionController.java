package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.Waste;
import com.doan.ecofootprint_be.entity.WaterConsumption;
import com.doan.ecofootprint_be.repository.WasteRepository;
import com.doan.ecofootprint_be.repository.WaterConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1")
@Validated
public class WaterConsumptionController {
    @Autowired
    private WaterConsumptionRepository waterConsumptionRepository;
    @GetMapping("/water")
    public List<WaterConsumption> findAllAcount(){
        return waterConsumptionRepository.findAll();
    }
    @GetMapping("/water/{date}")
    public WaterConsumption findDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return waterConsumptionRepository.findByDate(date);
    }
    @PostMapping("/water")
    ResponseEntity<?> create(@RequestBody WaterConsumption waterConsumption) {

        WaterConsumption created = waterConsumptionRepository.save(waterConsumption);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/water/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        waterConsumptionRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/water/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody WaterConsumption waterConsumption) {
        Optional<WaterConsumption> existingLogOptional = waterConsumptionRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            WaterConsumption existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setDate(waterConsumption.getDate());
            existingLog.setUnit(waterConsumption.getUnit());
            existingLog.setUsageType(waterConsumption.getUsageType());
            existingLog.setDescription(waterConsumption.getDescription());
            existingLog.setConsumption(waterConsumption.getConsumption());
            // Save the updated entity
            WaterConsumption updatedLog = waterConsumptionRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
}
    @GetMapping(value = "/waterconsumptiondate/{date}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // get entity
        boolean result = waterConsumptionRepository.existsByDate(date);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
