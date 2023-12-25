package com.doan.ecofootprint_be.service;

import com.doan.ecofootprint_be.entity.EcoFootprintLog;
import com.doan.ecofootprint_be.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LogService implements ILogService{
    private final LogRepository logRepository;
    @Override
    public EcoFootprintLog createLog(EcoFootprintLog ecoFootprintLog) {
        logRepository.save(ecoFootprintLog);
        return ecoFootprintLog;
    }

    @Override
    public EcoFootprintLog updateLog(int id, EcoFootprintLog ecoFootprintLog) {
        EcoFootprintLog entity = logRepository.findById(id).get();
        entity.setId(ecoFootprintLog.getId());
        entity.setFootprintType(ecoFootprintLog.getFootprintType());
        entity.setLogTime(ecoFootprintLog.getLogTime());
        entity.setLogDescription(ecoFootprintLog.getLogDescription());
        logRepository.save(entity);
        return entity;
    }

    @Override
    public void deleteLog(int id) {
        logRepository.deleteById(id);
    }

    @Override
    public List<EcoFootprintLog> getAll() {
        return logRepository.findAll();
    }
}
