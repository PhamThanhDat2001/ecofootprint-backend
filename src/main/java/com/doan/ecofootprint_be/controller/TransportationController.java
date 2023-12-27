package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import com.doan.ecofootprint_be.entity.Transportation;
import com.doan.ecofootprint_be.repository.GreenEnergyUsageRepository;
import com.doan.ecofootprint_be.repository.TransportationRepository;
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
public class TransportationController {
    @Autowired
    private TransportationRepository transportationRepository;
    @GetMapping("/transportation")
    public List<Transportation> findAllAcount(){
        return transportationRepository.findAll();
    }
    @GetMapping("/transportation/{date}")
    public Transportation findDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return transportationRepository.findByDate(date);
    }
    @PostMapping("/transportation")
    ResponseEntity<?> create(@RequestBody Transportation transportation) {

        Transportation created = transportationRepository.save(transportation);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/transportation/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        transportationRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/transportation/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Transportation transportation) {
        Optional<Transportation> existingLogOptional = transportationRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            Transportation existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setDate(transportation.getDate());
            existingLog.setUnit(transportation.getUnit());
            existingLog.setTransportMode(transportation.getTransportMode());
            existingLog.setDescription(transportation.getDescription());
            existingLog.setDistance(transportation.getDistance());
            // Save the updated entity
            Transportation updatedLog = transportationRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(value = "/transportationdate/{date}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // get entity
        boolean result = transportationRepository.existsByDate(date);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
