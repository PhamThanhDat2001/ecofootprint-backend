package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.Ranking;
import com.doan.ecofootprint_be.repository.RankingtRepository;
import com.doan.ecofootprint_be.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class RankingController {

    @Autowired
    private RankingService rankingService;
    @Autowired
    private RankingtRepository rankingRepository;
//    @GetMapping("/ranking")
//    public ResponseEntity<List<Ranking>> getAllRankings() {
//        List<Ranking> rankings = rankingRepository.findAll();
//        return ResponseEntity.ok(rankings);
//    }
@GetMapping("/ranking")
public ResponseEntity<List<Ranking>> getAllRankings() {
    List<Ranking> rankings = rankingRepository.getDistinctData();
    return ResponseEntity.ok(rankings);
}
//    @PostMapping("/ranking")
//    public ResponseEntity<String> updateRanking() {
//        rankingService.updateRanking();
//        return ResponseEntity.ok("Ranking updated successfully.");
//    }
@PostMapping("/ranking")
public ResponseEntity<String> updateRanking() {
    rankingService.updateRanking();
    return ResponseEntity.ok("Ranking updated successfully.");
}
}
