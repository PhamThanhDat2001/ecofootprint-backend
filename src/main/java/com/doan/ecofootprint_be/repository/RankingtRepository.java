package com.doan.ecofootprint_be.repository;

import com.doan.ecofootprint_be.entity.Ranking;
import com.doan.ecofootprint_be.entity.ResultEcoFootprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RankingtRepository extends JpaRepository<Ranking, Integer> {
    Optional<Ranking> findByUserid(Long user_id);
//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO ranking (user_id, total_points, title, stt, username) " +
//            "SELECT user_id, SUM(result) AS total_result, 'Title Placeholder' AS title, " +
//            "ROW_NUMBER() OVER (ORDER BY SUM(result) DESC) AS stt, " +
//            "u.username " +
//            "FROM result_eco_footprint r " +
//            "JOIN users u ON r.user_id = u.id " +
//            "GROUP BY user_id " +
//            "ON DUPLICATE KEY UPDATE " +
//            "total_points = VALUES(total_points), " +
//            "title = VALUES(title), " +
//            "stt = VALUES(stt), " +
//            "username = VALUES(username)", nativeQuery = true)
//    void updateTotalPoints();
@Transactional
@Modifying
@Query(value = "CALL update_ranking()", nativeQuery = true)
void updateTotalPoints();

    @Query("SELECT DISTINCT new com.doan.ecofootprint_be.entity.Ranking(r.userid, r.username, r.total_points, r.title) FROM Ranking r")
    List<Ranking> getDistinctData();

}