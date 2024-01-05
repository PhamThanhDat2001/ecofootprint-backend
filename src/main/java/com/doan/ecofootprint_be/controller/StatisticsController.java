package com.doan.ecofootprint_be.controller;


import com.doan.ecofootprint_be.entity.NewSpaper;
import com.doan.ecofootprint_be.entity.Statistics;
import com.doan.ecofootprint_be.repository.NewSpaperRepository;
import com.doan.ecofootprint_be.repository.StatisticsRepository;
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
public class StatisticsController {
    @Autowired
    private StatisticsRepository statisticsRepository;
    @GetMapping("/statistics")
    public List<Statistics> findAllAcount(){
        return statisticsRepository.findAll();
    }
    @PostMapping("/statistics")
    ResponseEntity<?> create(@RequestBody Statistics statistics) {

        Statistics created = statisticsRepository.save(statistics);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/statistics/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        statisticsRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/statistics/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody  Statistics statistics) {
        Optional<Statistics> existingLogOptional = statisticsRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            Statistics existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setTitle(statistics.getTitle());
            existingLog.setDescription(statistics.getDescription());
            existingLog.setImage_url(statistics.getImage_url());
            // Save the updated entity
            Statistics updatedLog = statisticsRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
}
