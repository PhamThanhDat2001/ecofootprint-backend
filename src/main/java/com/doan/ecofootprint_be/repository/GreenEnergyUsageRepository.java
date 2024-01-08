package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import com.doan.ecofootprint_be.entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GreenEnergyUsageRepository extends JpaRepository<GreenEnergyUsage, Integer>, JpaSpecificationExecutor<GreenEnergyUsage> {
    public GreenEnergyUsage findByDate(Date date);
    public boolean existsByDate( Date date);
    public boolean existsByDateAndUserid( Date date, String user_id);

    GreenEnergyUsage findByDateAndUserid(Date date, String user_id);
    List<GreenEnergyUsage> findByUserid(String user_id);
}
