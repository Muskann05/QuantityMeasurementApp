package com.spring.measurement.service;

import com.spring.measurement.dto.QuantityInputDTO;
import com.spring.measurement.dto.QuantityResponseDTO;

public interface IQuantityMeasurementService {

    QuantityResponseDTO compare(QuantityInputDTO input);

    QuantityResponseDTO convert(QuantityInputDTO input);

    QuantityResponseDTO add(QuantityInputDTO input);

    QuantityResponseDTO subtract(QuantityInputDTO input);

    QuantityResponseDTO multiply(QuantityInputDTO input);

    QuantityResponseDTO divide(QuantityInputDTO input);
}