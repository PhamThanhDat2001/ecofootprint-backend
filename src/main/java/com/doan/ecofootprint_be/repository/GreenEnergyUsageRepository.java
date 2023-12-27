package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GreenEnergyUsageRepository extends JpaRepository<GreenEnergyUsage, Integer>, JpaSpecificationExecutor<GreenEnergyUsage> {
    public GreenEnergyUsage findByDate(Date date);
    public boolean existsByDate( Date date);
}
