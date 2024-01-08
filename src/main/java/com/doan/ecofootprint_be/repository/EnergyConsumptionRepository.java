package com.doan.ecofootprint_be.repository;

import com.doan.ecofootprint_be.entity.EcoFootprintLog;
import com.doan.ecofootprint_be.entity.EnergyConsumption;
import com.doan.ecofootprint_be.entity.Transportation;
import com.doan.ecofootprint_be.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, Integer>, JpaSpecificationExecutor<EnergyConsumption> {
public EnergyConsumption findByDate(Date date);
    public boolean existsByDate( Date date);
    public boolean existsByDateAndUserid( Date date, String user_id);

    EnergyConsumption findByDateAndUserid(Date date, String user_id);
    List<EnergyConsumption> findByUserid(String user_id);
}
