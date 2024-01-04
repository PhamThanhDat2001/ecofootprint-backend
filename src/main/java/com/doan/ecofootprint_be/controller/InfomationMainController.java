package com.doan.ecofootprint_be.controller;


import com.doan.ecofootprint_be.entity.EcoFootprintLog;
import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import com.doan.ecofootprint_be.entity.InfomationMain;
import com.doan.ecofootprint_be.repository.InfomationMainRepository;
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
public class InfomationMainController {
    @Autowired
    private InfomationMainRepository infomationMainRepository;
    @GetMapping("/infomationmain")
    public List<InfomationMain> findAllAcount(){
        return infomationMainRepository.findAll();
    }
    @PostMapping("/infomationmain")
    ResponseEntity<?> create(@RequestBody InfomationMain infomationMain) {

        InfomationMain created = infomationMainRepository.save(infomationMain);
        return ResponseEntity.ok(created);
    }
    @DeleteMapping("/infomationmain/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        infomationMainRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/infomationmain/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InfomationMain infomationMain) {
        Optional<InfomationMain> existingLogOptional = infomationMainRepository.findById(id);

        if (existingLogOptional.isPresent()) {
            InfomationMain existingLog = existingLogOptional.get();

            // Update the properties of existingLog with the properties from energyConsumption
            existingLog.setTitle(infomationMain.getTitle());
            existingLog.setDescription(infomationMain.getDescription());
            existingLog.setImage_url(infomationMain.getImage_url());
            existingLog.setLink_url(infomationMain.getLink_url());
            // Save the updated entity
            InfomationMain updatedLog = infomationMainRepository.save(existingLog);

            return ResponseEntity.ok(updatedLog);
        } else {
            // Log with the specified ID was not found
            return ResponseEntity.notFound().build();
        }
    }
}
