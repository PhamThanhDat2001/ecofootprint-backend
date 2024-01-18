package com.doan.ecofootprint_be.controller;

import com.doan.ecofootprint_be.entity.Answer;
import com.doan.ecofootprint_be.entity.Question;
import com.doan.ecofootprint_be.repository.AnswerRepository;
import com.doan.ecofootprint_be.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/questions/{questionId}/answers")
    public List<Answer> getAnswersForQuestion(@PathVariable Long questionId) {
        // Fetch answers associated with the specified questionId
        return answerRepository.findByQuestionId(questionId);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer createAnswerForQuestion(@PathVariable Long questionId, @RequestBody Answer answer) {
        // Set the questionId for the answer
        answer.setQuestionId(questionId);

        // Save the answer
        return answerRepository.save(answer);
    }
}
