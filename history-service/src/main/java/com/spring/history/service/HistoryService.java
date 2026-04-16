package com.spring.history.service;

import com.spring.history.entity.HistoryEntity;
import java.util.List;

public interface HistoryService {

    HistoryEntity save(HistoryEntity history);

    List<HistoryEntity> getByUser(Long userId);

    List<HistoryEntity> getByOperation(String operation);
}