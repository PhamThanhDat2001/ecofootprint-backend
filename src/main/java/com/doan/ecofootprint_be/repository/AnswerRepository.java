package com.doan.ecofootprint_be.repository;

import com.doan.ecofootprint_be.entity.Answer;
import com.doan.ecofootprint_be.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
}
