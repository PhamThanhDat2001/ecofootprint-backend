package com.doan.ecofootprint_be.controller;


import com.doan.ecofootprint_be.entity.FoodConsumption;

import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import com.doan.ecofootprint_be.repository.FoodConsumptionRepository;
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
public class FoodConsumptionController {
    @Autowired
    private FoodConsumptionRepository foodConsumptionRepository;
    @GetMapping("/foodconsumption/{user_id}")
    public ResponseEntity<List<FoodConsumption>> findWaterConsumptionsByUserId(
            @PathVariable String user_id) {
        List<FoodConsumption> transportation = foodConsumptionRepository.findByUserid(user_id);

        if (!transportation.isEmpty()) {
            return ResponseEntity.ok(transportation);
        } else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
            return ResponseEntity.ok(Collections.emptyList()); // Return an empty list
        }
    }


    @GetMapping("/foodconsumptionn/{date}")
    public FoodConsumption findDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return foodConsumptionRepository.findByDate(date);
    }

    @GetMapping("/foodconsumption/{user_id}/{date}")
    public ResponseEntity<FoodConsumption> findWaterConsumptionByDateAndUserId(
            @PathVariable String user_id,
            @PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        FoodConsumption  foodConsumption= foodConsumptionRepository.findByDateAndUserid(date, user_id);
        if (foodConsumption != null) {
            return ResponseEntity.ok(foodConsumption);
        }
        else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
        }
        return null;
    }




    @PostMapping("/foodconsumption")
    ResponseEntity<?> create(@RequestBody FoodConsumption  foodConsumption) {

        FoodConsumption created = foodConsumptionRepository.save(foodConsumption);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/foodconsumption/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        foodConsumptionRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/foodconsumption/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody FoodConsumption  foodConsumption) {
        Optional<FoodConsumption> existingLogOptional = foodConsumptionRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            FoodConsumption existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setDate(foodConsumption.getDate());
            existingLog.setUnit(foodConsumption.getUnit());
            existingLog.setFoodItem(foodConsumption.getFoodItem());
            existingLog.setDescription(foodConsumption.getDescription());
            existingLog.setQuantity(foodConsumption.getQuantity());
            // Save the updated entity
            FoodConsumption updatedLog = foodConsumptionRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(value = "/foodconsumptiondate/{date}")
    public ResponseEntity<?> existsUserByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // get entity
        boolean result = foodConsumptionRepository.existsByDate(date);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping(value = "/foodconsumptiondate/{date}/{user_id}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                  @PathVariable String user_id) {
        // get entity
        boolean result = foodConsumptionRepository.existsByDateAndUserid(date,user_id);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
