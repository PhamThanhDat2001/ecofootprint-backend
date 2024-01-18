package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.Question;
import com.doan.ecofootprint_be.entity.Ranking;
import com.doan.ecofootprint_be.repository.QuestionRepository;
import com.doan.ecofootprint_be.repository.RankingtRepository;
import com.doan.ecofootprint_be.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/questions")
    public Question createQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
