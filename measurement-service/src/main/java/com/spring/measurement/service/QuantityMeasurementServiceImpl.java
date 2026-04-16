package com.spring.measurement.service;

import com.spring.measurement.dto.QuantityInputDTO;
import com.spring.measurement.dto.QuantityResponseDTO;
import com.spring.measurement.entity.QuantityMeasurementEntity;
import com.spring.measurement.repository.QuantityMeasurementRepository;
import com.spring.measurement.unit.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String HISTORY_URL = "http://HISTORY-SERVICE/api/history";

    // ================= COMMON =================

    private QuantityResponseDTO createResponse() {
        QuantityResponseDTO res = new QuantityResponseDTO();
        res.setError(false);
        return res;
    }

    private void validateType(QuantityInputDTO input) {
        if (!input.getThisQuantityDTO().getMeasurementType()
                .equals(input.getThatQuantityDTO().getMeasurementType())) {
            throw new RuntimeException("Different measurement types not allowed");
        }
    }

    // ================= CONVERSION =================

    private double convertToBase(double value, String unit, String type) {
        switch (type) {
            case "LengthUnit":
                return LengthUnit.valueOf(unit).toBaseUnit(value);
            case "WeightUnit":
                return WeightUnit.valueOf(unit).toBaseUnit(value);
            case "VolumeUnit":
                return VolumeUnit.valueOf(unit).toBaseUnit(value);
            case "TemperatureUnit":
                return TemperatureUnit.valueOf(unit).toBaseUnit(value);
            default:
                throw new RuntimeException("Invalid type");
        }
    }

    private double convertFromBase(double value, String unit, String type) {
        switch (type) {
            case "LengthUnit":
                return LengthUnit.valueOf(unit).fromBaseUnit(value);
            case "WeightUnit":
                return WeightUnit.valueOf(unit).fromBaseUnit(value);
            case "VolumeUnit":
                return VolumeUnit.valueOf(unit).fromBaseUnit(value);
            case "TemperatureUnit":
                return TemperatureUnit.valueOf(unit).fromBaseUnit(value);
            default:
                throw new RuntimeException("Invalid type");
        }
    }

    // ================= SAVE =================

    private void saveMeasurement(QuantityInputDTO input, QuantityResponseDTO res, String operation) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setUserId(input.getUserId());

        entity.setThisValue(input.getThisQuantityDTO().getValue());
        entity.setThisUnit(input.getThisQuantityDTO().getUnit());
        entity.setThisMeasurementType(input.getThisQuantityDTO().getMeasurementType());

        entity.setThatValue(input.getThatQuantityDTO().getValue());
        entity.setThatUnit(input.getThatQuantityDTO().getUnit());
        entity.setThatMeasurementType(input.getThatQuantityDTO().getMeasurementType());

        entity.setOperation(operation);

        entity.setResultValue(res.getResultValue());
        entity.setResultUnit(res.getResultUnit());
        entity.setResultString(res.getResultString());

        entity.setError(res.isError());
        entity.setErrorMessage(res.getErrorMessage());

        repository.save(entity);
    }

    private void saveToHistory(QuantityInputDTO input, QuantityResponseDTO res, String operation) {

        Map<String, Object> history = new HashMap<>();

        history.put("userId", input.getUserId());

        history.put("thisValue", input.getThisQuantityDTO().getValue());
        history.put("thisUnit", input.getThisQuantityDTO().getUnit());
        history.put("thisMeasurementType", input.getThisQuantityDTO().getMeasurementType());

        history.put("thatValue", input.getThatQuantityDTO().getValue());
        history.put("thatUnit", input.getThatQuantityDTO().getUnit());
        history.put("thatMeasurementType", input.getThatQuantityDTO().getMeasurementType());

        history.put("operation", operation);

        history.put("resultValue", res.getResultValue());
        history.put("resultUnit", res.getResultUnit());

        restTemplate.postForObject(HISTORY_URL, history, Void.class);
    }

    // ================= OPERATIONS =================

    private QuantityResponseDTO performArithmetic(QuantityInputDTO input, String operation) {

        validateType(input);
        QuantityResponseDTO res = createResponse();

        String type = input.getThisQuantityDTO().getMeasurementType();
        String resultUnit = input.getThisQuantityDTO().getUnit();

        double thisValue = convertToBase(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                type
        );

        double thatValue = convertToBase(
                input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(),
                type
        );

        double resultBase;

        switch (operation) {
            case "ADD":
                resultBase = thisValue + thatValue;
                break;

            case "SUBTRACT":
                resultBase = thisValue - thatValue;
                break;

            case "MULTIPLY":
                resultBase = thisValue * thatValue;
                break;

            case "DIVIDE":
                if (thatValue == 0) {
                    res.setError(true);
                    res.setErrorMessage("Cannot divide by zero");
                    res.setResultValue(0.0);
                    res.setResultUnit(resultUnit);
                    res.setResultString("DIVIDE FAILED");

                    saveMeasurement(input, res, operation);
                    saveToHistory(input, res, operation);
                    return res;
                }
                resultBase = thisValue / thatValue;
                break;

            default:
                throw new RuntimeException("Invalid operation");
        }

        double finalResult = convertFromBase(resultBase, resultUnit, type);

        res.setResultValue(finalResult);
        res.setResultUnit(resultUnit);
        res.setResultString(operation + " SUCCESS");

        saveMeasurement(input, res, operation);
        saveToHistory(input, res, operation);

        return res;
    }

    @Override
    public QuantityResponseDTO add(QuantityInputDTO input) {
        return performArithmetic(input, "ADD");
    }

    @Override
    public QuantityResponseDTO subtract(QuantityInputDTO input) {
        return performArithmetic(input, "SUBTRACT");
    }

    @Override
    public QuantityResponseDTO multiply(QuantityInputDTO input) {
        return performArithmetic(input, "MULTIPLY");
    }

    @Override
    public QuantityResponseDTO divide(QuantityInputDTO input) {
        return performArithmetic(input, "DIVIDE");
    }

    // ================= CONVERT =================

    @Override
    public QuantityResponseDTO convert(QuantityInputDTO input) {

        validateType(input);
        QuantityResponseDTO res = createResponse();

        String type = input.getThisQuantityDTO().getMeasurementType();

        double base = convertToBase(
                input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(),
                type
        );

        double result = convertFromBase(
                base,
                input.getThatQuantityDTO().getUnit(),
                type
        );

        res.setResultValue(result);
        res.setResultUnit(input.getThatQuantityDTO().getUnit());
        res.setResultString("CONVERT SUCCESS");

        saveMeasurement(input, res, "CONVERT");
        saveToHistory(input, res, "CONVERT");

        return res;
    }

	@Override
	public QuantityResponseDTO compare(QuantityInputDTO input) {
		// TODO Auto-generated method stub
		return null;
	}
}