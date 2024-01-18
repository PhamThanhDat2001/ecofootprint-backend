package com.doan.ecofootprint_be.repository;

import com.doan.ecofootprint_be.entity.NewSpaper;
import com.doan.ecofootprint_be.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
