package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import com.doan.ecofootprint_be.entity.Transportation;
import com.doan.ecofootprint_be.entity.WaterConsumption;
import com.doan.ecofootprint_be.repository.GreenEnergyUsageRepository;
import com.doan.ecofootprint_be.repository.TransportationRepository;
import com.doan.ecofootprint_be.repository.WaterConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    //    @GetMapping("/water")
//    public List<WaterConsumption> findAllAcount(){
//        return waterConsumptionRepository.findAll();
//    }
    @GetMapping("/transportation/{user_id}")
    public ResponseEntity<List<Transportation>> findWaterConsumptionsByUserId(
            @PathVariable String user_id) {
        List<Transportation> transportation = transportationRepository.findByUserid(user_id);

        if (!transportation.isEmpty()) {
            return ResponseEntity.ok(transportation);
        } else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
            return ResponseEntity.ok(Collections.emptyList()); // Return an empty list
        }
    }


    @GetMapping("/transportationn/{date}")
    public Transportation findDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return transportationRepository.findByDate(date);
    }

    @GetMapping("/transportation/{user_id}/{date}")
    public ResponseEntity<Transportation> findWaterConsumptionByDateAndUserId(
            @PathVariable String user_id,
            @PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        Transportation transportation = transportationRepository.findByDateAndUserid(date, user_id);
        if (transportation != null) {
            return ResponseEntity.ok(transportation);
        }
        else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
        }
        return null;
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
    public ResponseEntity<?> existsUserByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // get entity
        boolean result = transportationRepository.existsByDate(date);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping(value = "/transportationdate/{date}/{user_id}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                  @PathVariable String user_id) {
        // get entity
        boolean result = transportationRepository.existsByDateAndUserid(date,user_id);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
