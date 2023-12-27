package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.Transportation;
import com.doan.ecofootprint_be.entity.Waste;
import com.doan.ecofootprint_be.repository.TransportationRepository;
import com.doan.ecofootprint_be.repository.WasteRepository;
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
public class WasteController {
    @Autowired
    private WasteRepository wasteRepository;
    @GetMapping("/waste")
    public List<Waste> findAllAcount(){
        return wasteRepository.findAll();
    }
    @GetMapping("/waste/{date}")
    public Waste findDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return wasteRepository.findByDate(date);
    }
    @PostMapping("/waste")
    ResponseEntity<?> create(@RequestBody Waste waste) {

        Waste created = wasteRepository.save(waste);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/waste/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        wasteRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/waste/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Waste waste) {
        Optional<Waste> existingLogOptional = wasteRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            Waste existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setDate(waste.getDate());
            existingLog.setUnit(waste.getUnit());
            existingLog.setWasteType(waste.getWasteType());
            existingLog.setDescription(waste.getDescription());
            existingLog.setAmount(waste.getAmount());
            // Save the updated entity
            Waste updatedLog = wasteRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(value = "/wastedate/{date}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // get entity
        boolean result = wasteRepository.existsByDate(date);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
