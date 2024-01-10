package com.doan.ecofootprint_be.service;

import com.doan.ecofootprint_be.entity.Ranking;
import com.doan.ecofootprint_be.repository.RankingtRepository;
import com.doan.ecofootprint_be.repository.ResultEcoFootprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class RankingService {

    @Autowired
    private ResultEcoFootprintRepository resultEcoFootprintRepository;

    @Autowired
    private RankingtRepository rankingRepository;

//    public void updateRanking() {
//        List<Object[]> userRankingData = resultEcoFootprintRepository.calculateTotalResultByUser();
//
//        for (Object[] userData : userRankingData) {
//            Long userId = (Long) userData[0];
//            BigDecimal totalResult = (BigDecimal) userData[1];
//
//            Ranking ranking = rankingRepository.findByUserid(userId)
//                    .orElse(new Ranking(userId, BigDecimal.ZERO));
//
//            ranking.setTotal_points(totalResult);
//            rankingRepository.save(ranking);
//        }
//    }
    @Transactional
    public void updateRanking() {
        rankingRepository.updateTotalPoints();
    }


}