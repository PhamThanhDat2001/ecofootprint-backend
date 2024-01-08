package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.Waste;
import com.doan.ecofootprint_be.entity.WaterConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WasteRepository extends JpaRepository<Waste, Integer>, JpaSpecificationExecutor<Waste> {
    public Waste findByDate(Date date);
    public boolean existsByDate( Date date);
    public boolean existsByDateAndUserid( Date date, String user_id);

    Waste findByDateAndUserid(Date date, String user_id);
    List<Waste> findByUserid(String user_id);
}
