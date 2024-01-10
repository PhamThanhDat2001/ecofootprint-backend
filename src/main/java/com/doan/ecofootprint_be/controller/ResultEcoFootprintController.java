package com.doan.ecofootprint_be.controller;


import com.doan.ecofootprint_be.entity.InfomationMain;
import com.doan.ecofootprint_be.entity.ResultEcoFootprint;
import com.doan.ecofootprint_be.repository.InfomationMainRepository;
import com.doan.ecofootprint_be.repository.ResultEcoFootprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1")
@Validated
public class ResultEcoFootprintController {
    @Autowired
    private ResultEcoFootprintRepository resultEcoFootprintRepository;
    @GetMapping(value = "/resultecofootprint/username/{username}")
    public ResponseEntity<?> existsUserByUserName(@PathVariable(name = "username") String username) {
        // get entity
        boolean result = resultEcoFootprintRepository.existsByUsername(username);

        // return result
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/resultecofootprint")
    public List<ResultEcoFootprint> findAllAcount(){
        return resultEcoFootprintRepository.findAll();
    }
    @PostMapping("/resultecofootprint")
    ResponseEntity<?> create(@RequestBody ResultEcoFootprint resultEcoFootprint) {

        ResultEcoFootprint created = resultEcoFootprintRepository.save(resultEcoFootprint);
        return ResponseEntity.ok(created);
    }



    @DeleteMapping("/resultecofootprint/{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        resultEcoFootprintRepository.deleteById(id);
        return ResponseEntity.ok("Deleted successfully.");
    }

@PutMapping("/resultecofootprint/user/{user_id}")
public ResponseEntity<?> updateByUserId(@PathVariable String user_id, @RequestBody ResultEcoFootprint resultEcoFootprint) {
    Optional<ResultEcoFootprint> existingLogOptional = resultEcoFootprintRepository.findByUser_id(user_id);

    if (existingLogOptional.isPresent()) {
        ResultEcoFootprint existingLog = existingLogOptional.get();

        // Update the properties of existingLog with the properties from resultEcoFootprint
        existingLog.setUsername(resultEcoFootprint.getUsername());
        existingLog.setResult(resultEcoFootprint.getResult());
        existingLog.setDate(resultEcoFootprint.getDate());

        // Save the updated entity
        ResultEcoFootprint updatedLog = resultEcoFootprintRepository.save(existingLog);

        return ResponseEntity.ok(updatedLog);
    } else {
        // Log with the specified user_id was not found
        return ResponseEntity.notFound().build();
    }
}

}
