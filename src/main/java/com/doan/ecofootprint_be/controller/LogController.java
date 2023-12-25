package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.EcoFootprintLog;
import com.doan.ecofootprint_be.service.ILogService;
import com.doan.ecofootprint_be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1")
@Validated
public class LogController {
    @Autowired
    private ILogService logService;
    @GetMapping("")
    public List<EcoFootprintLog> findAllAcount(){
        return logService.getAll();
    }
    @PostMapping("")
    ResponseEntity<?> createLog(@RequestBody EcoFootprintLog ecoFootprintLog) {

        EcoFootprintLog createdLog = logService.createLog(ecoFootprintLog);
        return ResponseEntity.ok(createdLog);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLog(@PathVariable int id) {
        logService.deleteLog(id);
        return ResponseEntity.ok("Deleted successfully.");
    }
    @PutMapping("/{id}")
    ResponseEntity<?> updateLog(@PathVariable int id, @RequestBody EcoFootprintLog ecoFootprintLog) {

        EcoFootprintLog createdLog = logService.updateLog(id, ecoFootprintLog);
        return ResponseEntity.ok(createdLog);
    }

}
