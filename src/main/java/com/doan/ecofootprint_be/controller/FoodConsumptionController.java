package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.EnergyConsumption;
import com.doan.ecofootprint_be.entity.FoodConsumption;
import com.doan.ecofootprint_be.repository.EnergyConsumptionRepository;
import com.doan.ecofootprint_be.repository.FoodConsumptionRepository;
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
public class FoodConsumptionController {
    @Autowired
    private FoodConsumptionRepository foodConsumptionRepository;
    @GetMapping("/foodconsumption")
    public List<FoodConsumption> findAllAcount(){
        return foodConsumptionRepository.findAll();
    }
    @PostMapping("/foodconsumption")
    ResponseEntity<?> create(@RequestBody FoodConsumption foodConsumption) {

        FoodConsumption created = foodConsumptionRepository.save(foodConsumption);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/foodconsumption/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        foodConsumptionRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/foodconsumption/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody FoodConsumption foodConsumption) {
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
}
