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

import java.util.Collections;
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
//    @GetMapping("/water")
//    public List<WaterConsumption> findAllAcount(){
//        return waterConsumptionRepository.findAll();
//    }
@GetMapping("/water/{user_id}")
public ResponseEntity<List<WaterConsumption>> findWaterConsumptionsByUserId(
        @PathVariable String user_id) {
    List<WaterConsumption> waterConsumptions = waterConsumptionRepository.findByUserid(user_id);

    if (!waterConsumptions.isEmpty()) {
        return ResponseEntity.ok(waterConsumptions);
    } else {
        System.out.println("Water consumption not found for user_id: " + user_id);
        // Any additional logic or statements if needed
        return ResponseEntity.ok(Collections.emptyList()); // Return an empty list
    }
}


    @GetMapping("/waterr/{date}")
    public WaterConsumption findDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return waterConsumptionRepository.findByDate(date);
    }

    @GetMapping("/water/{user_id}/{date}")
    public ResponseEntity<WaterConsumption> findWaterConsumptionByDateAndUserId(
            @PathVariable String user_id,
            @PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
            ) {
        WaterConsumption waterConsumption = waterConsumptionRepository.findByDateAndUserid(date, user_id);
        if (waterConsumption != null) {
            return ResponseEntity.ok(waterConsumption);
        }
        else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
        }
        return null;
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
    public ResponseEntity<?> existsUserByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // get entity
        boolean result = waterConsumptionRepository.existsByDate(date);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping(value = "/waterconsumptiondate/{date}/{user_id}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                  @PathVariable String user_id) {
        // get entity
        boolean result = waterConsumptionRepository.existsByDateAndUserid(date,user_id);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
