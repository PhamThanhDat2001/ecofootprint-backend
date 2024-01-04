package com.doan.ecofootprint_be.controller;


import com.doan.ecofootprint_be.entity.InfomationMain;
import com.doan.ecofootprint_be.entity.InfomationOther;
import com.doan.ecofootprint_be.entity.InfomationSecond;
import com.doan.ecofootprint_be.repository.InfomationMainRepository;
import com.doan.ecofootprint_be.repository.InfomationOtherRepository;
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
public class InfomationOtherController {
    @Autowired
    private InfomationOtherRepository infomationOtherRepository;
    @GetMapping("/infomationother")
    public List<InfomationOther> findAllAcount(){
        return infomationOtherRepository.findAll();
    }
    @PostMapping("/infomationother")
    ResponseEntity<?> create(@RequestBody InfomationOther infomationOther) {

        InfomationOther created = infomationOtherRepository.save(infomationOther);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/infomationother/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        infomationOtherRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/infomationother/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InfomationOther infomationOther) {
        Optional<InfomationOther> existingLogOptional = infomationOtherRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            InfomationOther existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setTitle(infomationOther.getTitle());
            existingLog.setDescription(infomationOther.getDescription());
            existingLog.setImage_url(infomationOther.getImage_url());
            existingLog.setLink_url(infomationOther.getLink_url());
            // Save the updated entity
            InfomationOther updatedLog = infomationOtherRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
}
