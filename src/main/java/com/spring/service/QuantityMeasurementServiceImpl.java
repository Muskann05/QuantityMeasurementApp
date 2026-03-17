package com.spring.service;

import com.spring.dto.QuantityInputDTO;
import com.spring.entity.QuantityMeasurementEntity;
import com.spring.repository.QuantityMeasurementRepository;
import com.spring.unit.LengthUnit;
import com.spring.unit.TemperatureUnit;
import com.spring.unit.VolumeUnit;
import com.spring.unit.WeightUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    //  CONVERSION

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
            default: throw new RuntimeException("Invalid measurement type: " + type);
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
            default: throw new RuntimeException("Invalid measurement type: " + type);
        }
    }

    private void validateType(QuantityInputDTO input) {
        String type1 = input.getThisQuantityDTO().getMeasurementType();
        String type2 = input.getThatQuantityDTO().getMeasurementType();
        if (!type1.equals(type2)) {
            throw new RuntimeException("Different measurement types not allowed");
        }
    }

    //  COMPARE 

    @Override
    public QuantityMeasurementEntity compare(QuantityInputDTO input) {
        validateType(input);
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        String type = input.getThisQuantityDTO().getMeasurementType();
        double thisValue = convertToBase(input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(), type);
        double thatValue = convertToBase(input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(), type);

        boolean result = Math.abs(thisValue - thatValue) < 0.0001;

        entity.setOperation("COMPARE");
        entity.setResultString(String.valueOf(result));
        return repository.save(entity);
    }

    // CONVERT 

    @Override
    public QuantityMeasurementEntity convert(QuantityInputDTO input) {
        validateType(input);
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        String type = input.getThisQuantityDTO().getMeasurementType();
        double thisValue = input.getThisQuantityDTO().getValue();
        String fromUnit = input.getThisQuantityDTO().getUnit();
        String toUnit = input.getThatQuantityDTO().getUnit();

        double base = convertToBase(thisValue, fromUnit, type);
        double result = convertFromBase(base, toUnit, type);

        entity.setOperation("CONVERT");
        entity.setResultValue(result);
        entity.setResultUnit(toUnit);
        return repository.save(entity);
    }

    //  GENERAL ARITHMETIC 

    private QuantityMeasurementEntity performArithmetic(QuantityInputDTO input, String operation) {
        validateType(input);
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        String type = input.getThisQuantityDTO().getMeasurementType();
        String resultUnit = input.getThisQuantityDTO().getUnit();

        // Convert operands to base
        double thisValue = convertToBase(input.getThisQuantityDTO().getValue(),
                input.getThisQuantityDTO().getUnit(), type);
        double thatValue = convertToBase(input.getThatQuantityDTO().getValue(),
                input.getThatQuantityDTO().getUnit(), type);

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
                    entity.setError(true);
                    entity.setErrorMessage("Cannot divide by zero");
                    return repository.save(entity);
                }
                resultBase = thisValue / thatValue;
                break;
            default: throw new RuntimeException("Invalid operation: " + operation);
        }

        double finalResult = convertFromBase(resultBase, resultUnit, type);
        entity.setResultValue(finalResult);
        entity.setResultUnit(resultUnit);
        entity.setOperation(operation);

        return repository.save(entity);
    }

    @Override
    public QuantityMeasurementEntity add(QuantityInputDTO input) { 
    	return performArithmetic(input, "ADD"); 
    }

    @Override
    public QuantityMeasurementEntity subtract(QuantityInputDTO input) { 
    	return performArithmetic(input, "SUBTRACT"); 
    }

    @Override
    public QuantityMeasurementEntity multiply(QuantityInputDTO input) { 
    	return performArithmetic(input, "MULTIPLY"); 
    }

    @Override
    public QuantityMeasurementEntity divide(QuantityInputDTO input) { 
    	return performArithmetic(input, "DIVIDE"); 
    }

    // HISTORY 

    @Override
    public List<QuantityMeasurementEntity> getHistoryByOperation(String operation) {
        return repository.findByOperation(operation);
    }

    // COUNT 

    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndErrorFalse(operation);
    }
}