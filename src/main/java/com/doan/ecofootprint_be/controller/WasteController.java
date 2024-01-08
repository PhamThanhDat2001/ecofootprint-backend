package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.Transportation;
import com.doan.ecofootprint_be.entity.Waste;
import com.doan.ecofootprint_be.entity.WaterConsumption;
import com.doan.ecofootprint_be.repository.TransportationRepository;
import com.doan.ecofootprint_be.repository.WasteRepository;
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
public class WasteController {
    @Autowired
    private WasteRepository wasteRepository;
    //    @GetMapping("/water")
//    public List<WaterConsumption> findAllAcount(){
//        return waterConsumptionRepository.findAll();
//    }
    @GetMapping("/waste/{user_id}")
    public ResponseEntity<List<Waste>> findWaterConsumptionsByUserId(
            @PathVariable String user_id) {
        List<Waste> wastes = wasteRepository.findByUserid(user_id);

        if (!wastes.isEmpty()) {
            return ResponseEntity.ok(wastes);
        } else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
            return ResponseEntity.ok(Collections.emptyList()); // Return an empty list
        }
    }


    @GetMapping("/wastee/{date}")
    public Waste findDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return wasteRepository.findByDate(date);
    }

    @GetMapping("/waste/{user_id}/{date}")
    public ResponseEntity<Waste> findWaterConsumptionByDateAndUserId(
            @PathVariable String user_id,
            @PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        Waste waste = wasteRepository.findByDateAndUserid(date, user_id);
        if (waste != null) {
            return ResponseEntity.ok(waste);
        }
        else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
        }
        return null;
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
    public ResponseEntity<?> existsUserByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // get entity
        boolean result = wasteRepository.existsByDate(date);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping(value = "/wastedate/{date}/{user_id}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                  @PathVariable String user_id) {
        // get entity
        boolean result = wasteRepository.existsByDateAndUserid(date,user_id);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
