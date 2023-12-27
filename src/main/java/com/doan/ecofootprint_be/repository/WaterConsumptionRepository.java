package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.WaterConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface WaterConsumptionRepository extends JpaRepository<WaterConsumption, Integer>, JpaSpecificationExecutor<WaterConsumption> {
    public WaterConsumption findByDate(Date date);
    public boolean existsByDate( Date date);
}
