package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.FoodConsumption;
import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import com.doan.ecofootprint_be.repository.FoodConsumptionRepository;
import com.doan.ecofootprint_be.repository.GreenEnergyUsageRepository;
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
public class GreenEnergyUsageController {
    @Autowired
    private GreenEnergyUsageRepository greenEnergyUsageRepository;
    //    @GetMapping("/water")
//    public List<WaterConsumption> findAllAcount(){
//        return waterConsumptionRepository.findAll();
//    }
    @GetMapping("/greenenergyusage/{user_id}")
    public ResponseEntity<List<GreenEnergyUsage>> findWaterConsumptionsByUserId(
            @PathVariable String user_id) {
        List<GreenEnergyUsage> transportation = greenEnergyUsageRepository.findByUserid(user_id);

        if (!transportation.isEmpty()) {
            return ResponseEntity.ok(transportation);
        } else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
            return ResponseEntity.ok(Collections.emptyList()); // Return an empty list
        }
    }


    @GetMapping("/greenenergyusagee/{date}")
    public GreenEnergyUsage findDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return greenEnergyUsageRepository.findByDate(date);
    }

    @GetMapping("/greenenergyusage/{user_id}/{date}")
    public ResponseEntity<GreenEnergyUsage> findWaterConsumptionByDateAndUserId(
            @PathVariable String user_id,
            @PathVariable(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        GreenEnergyUsage  greenEnergyUsage= greenEnergyUsageRepository.findByDateAndUserid(date, user_id);
        if (greenEnergyUsage != null) {
            return ResponseEntity.ok(greenEnergyUsage);
        }
        else {
            System.out.println("Water consumption not found for user_id: " + user_id);
            // Any additional logic or statements if needed
        }
        return null;
    }




    @PostMapping("/greenenergyusage")
    ResponseEntity<?> create(@RequestBody GreenEnergyUsage  greenEnergyUsage) {

        GreenEnergyUsage created = greenEnergyUsageRepository.save(greenEnergyUsage);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/greenenergyusage/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        greenEnergyUsageRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/greenenergyusage/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody GreenEnergyUsage  greenEnergyUsage) {
        Optional<GreenEnergyUsage> existingLogOptional = greenEnergyUsageRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            GreenEnergyUsage existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setDate(greenEnergyUsage.getDate());
            existingLog.setUnit(greenEnergyUsage.getUnit());
            existingLog.setEnergySource(greenEnergyUsage.getEnergySource());
            existingLog.setDescription(greenEnergyUsage.getDescription());
            existingLog.setUsageAmount(greenEnergyUsage.getUsageAmount());
            // Save the updated entity
            GreenEnergyUsage updatedLog = greenEnergyUsageRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(value = "/greenenergyusagedate/{date}")
    public ResponseEntity<?> existsUserByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        // get entity
        boolean result = greenEnergyUsageRepository.existsByDate(date);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping(value = "/greenenergyusagedate/{date}/{user_id}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                  @PathVariable String user_id) {
        // get entity
        boolean result = greenEnergyUsageRepository.existsByDateAndUserid(date,user_id);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
