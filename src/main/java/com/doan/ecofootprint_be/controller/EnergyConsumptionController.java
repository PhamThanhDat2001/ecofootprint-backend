package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.EcoFootprintLog;
import com.doan.ecofootprint_be.entity.EnergyConsumption;
import com.doan.ecofootprint_be.entity.FoodConsumption;
import com.doan.ecofootprint_be.repository.EnergyConsumptionRepository;
import com.doan.ecofootprint_be.service.ILogService;
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
public class EnergyConsumptionController {
    @Autowired
    private EnergyConsumptionRepository energyConsumptionRepository;
    @GetMapping("/energyconsumption/{user_id}")
    public ResponseEntity<List<EnergyConsumption>> findWaterConsumptionsByUserId(
            @PathVariable String user_id) {
        List<EnergyConsumption> energyConsumptions = energyConsumptionRepository.findByUserid(user_id);

        if (!energyConsumptions.isEmpty()) {
            return ResponseEntity.ok(energyConsumptions);
        } else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
            return ResponseEntity.ok(Collections.emptyList()); // Return an empty list
        }
    }


    @GetMapping("/energyconsumptionn/{date}")
    public EnergyConsumption findDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return energyConsumptionRepository.findByDate(date);
    }

    @GetMapping("/energyconsumption/{user_id}/{date}")
    public ResponseEntity<EnergyConsumption> findWaterConsumptionByDateAndUserId(
            @PathVariable String user_id,
            @PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        EnergyConsumption  energyConsumption= energyConsumptionRepository.findByDateAndUserid(date, user_id);
        if (energyConsumption != null) {
            return ResponseEntity.ok(energyConsumption);
        }
        else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
        }
        return null;
    }




    @PostMapping("/energyconsumption")
    ResponseEntity<?> create(@RequestBody EnergyConsumption  energyConsumption) {

        EnergyConsumption created = energyConsumptionRepository.save(energyConsumption);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/energyconsumption/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        energyConsumptionRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/energyconsumption/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody EnergyConsumption  energyConsumption) {
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
    @GetMapping(value = "/energyconsumptiondate/{date}")
    public ResponseEntity<?> existsUserByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // get entity
        boolean result = energyConsumptionRepository.existsByDate(date);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping(value = "/energyconsumptiondate/{date}/{user_id}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                  @PathVariable String user_id) {
        // get entity
        boolean result = energyConsumptionRepository.existsByDateAndUserid(date,user_id);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
