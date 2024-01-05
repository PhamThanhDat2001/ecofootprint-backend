package com.doan.ecofootprint_be.controller;


import com.doan.ecofootprint_be.entity.NewSpaper;
import com.doan.ecofootprint_be.entity.VideoEducation;
import com.doan.ecofootprint_be.repository.NewSpaperRepository;
import com.doan.ecofootprint_be.repository.VideoEducationRepository;
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
public class NewSpaperController {
    @Autowired
    private NewSpaperRepository newSpaperRepository;
    @GetMapping("/newspaper")
    public List<NewSpaper> findAllAcount(){
        return newSpaperRepository.findAll();
    }
    @PostMapping("/newspaper")
    ResponseEntity<?> create(@RequestBody NewSpaper newSpaper) {

        NewSpaper created = newSpaperRepository.save(newSpaper);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/newspaper/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        newSpaperRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/newspaper/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody NewSpaper newSpaper) {
        Optional<NewSpaper> existingLogOptional = newSpaperRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            NewSpaper existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setTitle(newSpaper.getTitle());
            existingLog.setDescription(newSpaper.getDescription());
            existingLog.setLink_url(newSpaper.getLink_url());
            // Save the updated entity
            NewSpaper updatedLog = newSpaperRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
}
