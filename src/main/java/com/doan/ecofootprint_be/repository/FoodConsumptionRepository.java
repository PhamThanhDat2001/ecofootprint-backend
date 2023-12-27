package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.FoodConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FoodConsumptionRepository extends JpaRepository<FoodConsumption, Integer>, JpaSpecificationExecutor<FoodConsumption> {
    public FoodConsumption findByDate(Date date);
    public boolean existsByDate( Date date);
}
