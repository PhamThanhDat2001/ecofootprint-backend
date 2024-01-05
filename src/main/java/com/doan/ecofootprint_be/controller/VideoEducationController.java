package com.doan.ecofootprint_be.controller;


import com.doan.ecofootprint_be.entity.InfomationMain;
import com.doan.ecofootprint_be.entity.VideoEducation;
import com.doan.ecofootprint_be.repository.InfomationMainRepository;
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
public class VideoEducationController {
    @Autowired
    private VideoEducationRepository videoEducationRepository;
    @GetMapping("/videoeducation")
    public List<VideoEducation> findAllAcount(){
        return videoEducationRepository.findAll();
    }
    @PostMapping("/videoeducation")
    ResponseEntity<?> create(@RequestBody VideoEducation videoEducation) {

        VideoEducation created = videoEducationRepository.save(videoEducation);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/videoeducation/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        videoEducationRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/videoeducation/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody VideoEducation videoEducation) {
        Optional<VideoEducation> existingLogOptional = videoEducationRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            VideoEducation existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setTitle(videoEducation.getTitle());
            existingLog.setDescription(videoEducation.getDescription());
            existingLog.setVideo_url(videoEducation.getVideo_url());
            // Save the updated entity
            VideoEducation updatedLog = videoEducationRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
}
