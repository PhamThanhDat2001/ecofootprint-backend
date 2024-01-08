package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.FoodConsumption;
import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FoodConsumptionRepository extends JpaRepository<FoodConsumption, Integer>, JpaSpecificationExecutor<FoodConsumption> {
    public FoodConsumption findByDate(Date date);
    public boolean existsByDate( Date date);
    public boolean existsByDateAndUserid( Date date, String user_id);

    FoodConsumption findByDateAndUserid(Date date, String user_id);
    List<FoodConsumption> findByUserid(String user_id);
}
