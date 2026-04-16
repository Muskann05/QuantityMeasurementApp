package com.spring.history.repository;

import com.spring.history.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

    List<HistoryEntity> findByUserId(Long userId);

    List<HistoryEntity> findByOperation(String operation);
}