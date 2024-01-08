package com.doan.ecofootprint_be.repository;

import com.doan.ecofootprint_be.entity.InfomationMain;
import com.doan.ecofootprint_be.entity.ResultEcoFootprint;
import com.doan.ecofootprint_be.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultEcoFootprintRepository extends JpaRepository<ResultEcoFootprint, Integer> {
    public boolean existsByUsername(String username);
    @Query("SELECT r FROM ResultEcoFootprint r WHERE r.user_id = :user_id")
    Optional<ResultEcoFootprint> findByUser_id(@Param("user_id") String user_id);
}