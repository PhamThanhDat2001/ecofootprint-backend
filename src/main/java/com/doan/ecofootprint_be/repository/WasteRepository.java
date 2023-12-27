package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface WasteRepository extends JpaRepository<Waste, Integer>, JpaSpecificationExecutor<Waste> {
    public Waste findByDate(Date date);
    public boolean existsByDate( Date date);
}
