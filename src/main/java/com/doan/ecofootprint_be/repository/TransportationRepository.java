package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Integer>, JpaSpecificationExecutor<Transportation> {
    public Transportation findByDate(Date date);
    public boolean existsByDate( Date date);
}
