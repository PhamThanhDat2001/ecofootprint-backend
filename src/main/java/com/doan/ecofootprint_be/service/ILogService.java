package com.doan.ecofootprint_be.service;

import com.doan.ecofootprint_be.entity.EcoFootprintLog;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ILogService {
    EcoFootprintLog createLog(EcoFootprintLog ecoFootprintLog);
    EcoFootprintLog updateLog(int id, EcoFootprintLog ecoFootprintLog);

    void deleteLog(int id);

    List<EcoFootprintLog> getAll();
}
