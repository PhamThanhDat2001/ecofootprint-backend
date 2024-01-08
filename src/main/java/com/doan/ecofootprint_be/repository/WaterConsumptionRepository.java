package com.doan.ecofootprint_be.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.doan.ecofootprint_be.entity.Users;
import com.doan.ecofootprint_be.entity.WaterConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface WaterConsumptionRepository extends JpaRepository<WaterConsumption, Integer>{
    public WaterConsumption findByDate(Date date);
    public boolean existsByDate( Date date);
    public boolean existsByDateAndUserid( Date date, String user_id);

    WaterConsumption findByDateAndUserid(Date date, String user_id);
    List<WaterConsumption> findByUserid(String user_id);

}
