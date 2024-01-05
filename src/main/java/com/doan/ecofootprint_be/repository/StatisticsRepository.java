package com.doan.ecofootprint_be.repository;

import com.doan.ecofootprint_be.entity.NewSpaper;
import com.doan.ecofootprint_be.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {
}
