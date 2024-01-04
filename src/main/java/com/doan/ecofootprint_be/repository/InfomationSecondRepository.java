package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.InfomationSecond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfomationSecondRepository extends JpaRepository<InfomationSecond, Integer> {
}
