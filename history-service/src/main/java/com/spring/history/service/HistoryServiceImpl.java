package com.spring.history.service;

import com.spring.history.entity.HistoryEntity;
import com.spring.history.repository.HistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository repository;

    @Override
    public HistoryEntity save(HistoryEntity history) {
        System.out.println(" Saving History for user: " + history.getUserId());
        return repository.save(history);
    }

    @Override
    public List<HistoryEntity> getByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<HistoryEntity> getByOperation(String operation) {
        return repository.findByOperation(operation);
    }
}