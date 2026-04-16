package com.spring.measurement.repository;
import com.spring.measurement.entity.QuantityMeasurementEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperation(String operation);

    List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);

    long countByOperationAndErrorFalse(String operation);

    List<QuantityMeasurementEntity> findByErrorTrue();

    // (USER BASED DATA)
    List<QuantityMeasurementEntity> findByUserId(Long userId);

    List<QuantityMeasurementEntity> findByUserIdAndOperation(Long userId, String operation);
}