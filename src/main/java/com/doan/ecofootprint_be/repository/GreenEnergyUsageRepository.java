package com.doan.ecofootprint_be.repository;

import com.doan.ecofootprint_be.entity.EcoFootprintLog;
import com.doan.ecofootprint_be.entity.GreenEnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenEnergyUsageRepository extends JpaRepository<GreenEnergyUsage, Integer> {
}
