package com.doan.ecofootprint_be.controller;



import com.doan.ecofootprint_be.entity.InfomationSecond;
import com.doan.ecofootprint_be.repository.InfomationSecondRepository;
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
public class InfomationSecondController {
    @Autowired
    private InfomationSecondRepository infomationSecondRepository;
    @GetMapping("/infomationsecond")
    public List<InfomationSecond> findAllAcount(){
        return infomationSecondRepository.findAll();
    }
    @PostMapping("/infomationsecond")
    ResponseEntity<?> create(@RequestBody InfomationSecond infomationSecond) {

        InfomationSecond created = infomationSecondRepository.save(infomationSecond);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/infomationsecond/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        infomationSecondRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/infomationsecond/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InfomationSecond infomationSecond) {
        Optional<InfomationSecond> existingLogOptional = infomationSecondRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            InfomationSecond existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setTitle(infomationSecond.getTitle());
            existingLog.setDescription(infomationSecond.getDescription());
            existingLog.setImage_url(infomationSecond.getImage_url());
            existingLog.setLink_url(infomationSecond.getLink_url());
            // Save the updated entity
            InfomationSecond updatedLog = infomationSecondRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
}
