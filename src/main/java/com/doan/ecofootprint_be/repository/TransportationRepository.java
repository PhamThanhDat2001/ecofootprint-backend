package com.doan.ecofootprint_be.repository;


import com.doan.ecofootprint_be.entity.Transportation;
import com.doan.ecofootprint_be.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Integer>, JpaSpecificationExecutor<Transportation> {
    public Transportation findByDate(Date date);
    public boolean existsByDate( Date date);
    public boolean existsByDateAndUserid( Date date, String user_id);

    Transportation findByDateAndUserid(Date date, String user_id);
    List<Transportation> findByUserid(String user_id);
}
