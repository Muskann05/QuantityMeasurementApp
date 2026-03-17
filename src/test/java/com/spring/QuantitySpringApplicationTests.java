package com.spring;

import com.spring.dto.QuantityInputDTO;
import com.spring.service.QuantityMeasurementServiceImpl;
import com.spring.dto.QuantityDTO;
import com.spring.entity.QuantityMeasurementEntity;
import com.spring.repository.QuantityMeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuantitySpringApplicationTest {

    @Mock
    private QuantityMeasurementRepository repository;

    @InjectMocks
    private QuantityMeasurementServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Helper to mock repository save
    private QuantityMeasurementEntity saveMock(QuantityMeasurementEntity entity) {
        when(repository.save(any())).thenReturn(entity);
        return entity;
    }

    // ==================== COMPARE ====================
    @Test
    void testCompare_Length_FeetInches() {
        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(new QuantityDTO(1, "FEET", "LengthUnit"));
        input.setThatQuantityDTO(new QuantityDTO(12, "INCHES", "LengthUnit")); // 1 foot = 12 inches

        QuantityMeasurementEntity saved = saveMock(new QuantityMeasurementEntity());
        QuantityMeasurementEntity result = service.compare(input);

        assertEquals("COMPARE", result.getOperation());
        assertTrue(Boolean.parseBoolean(result.getResultString()));
    }

    // ==================== CONVERT ====================
    @Test
    void testConvert_Weight_KGToGram() {
        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(new QuantityDTO(2, "KILOGRAM", "WeightUnit"));
        input.setThatQuantityDTO(new QuantityDTO(0, "GRAM", "WeightUnit"));

        QuantityMeasurementEntity saved = saveMock(new QuantityMeasurementEntity());
        QuantityMeasurementEntity result = service.convert(input);

        assertEquals("CONVERT", result.getOperation());
        assertEquals(2000.0, result.getResultValue(), 0.0001);
        assertEquals("GRAM", result.getResultUnit());
    }

    // ==================== ADD ====================
    @Test
    void testAdd_Length_YardsAndFeet() {
        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(new QuantityDTO(1, "YARDS", "LengthUnit")); // 1 yard = 3 feet
        input.setThatQuantityDTO(new QuantityDTO(2, "FEET", "LengthUnit"));

        QuantityMeasurementEntity saved = saveMock(new QuantityMeasurementEntity());
        QuantityMeasurementEntity result = service.add(input);

        assertEquals("ADD", result.getOperation());
        assertEquals(5, result.getResultValue(), 0.0001); // 3+2=5 feet
        assertEquals("YARDS", result.getResultUnit());
    }

    // ==================== SUBTRACT ====================
    @Test
    void testSubtract_Weight_KGAndGram() {
        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(new QuantityDTO(2, "KILOGRAM", "WeightUnit"));
        input.setThatQuantityDTO(new QuantityDTO(500, "GRAM", "WeightUnit"));

        QuantityMeasurementEntity saved = saveMock(new QuantityMeasurementEntity());
        QuantityMeasurementEntity result = service.subtract(input);

        assertEquals("SUBTRACT", result.getOperation());
        assertEquals(1.5, result.getResultValue(), 0.0001); // 2kg - 0.5kg
        assertEquals("KILOGRAM", result.getResultUnit());
    }

    // ==================== MULTIPLY ====================
    @Test
    void testMultiply_Volume_LitreAndMillilitre() {
        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(new QuantityDTO(1, "LITRE", "VolumeUnit"));
        input.setThatQuantityDTO(new QuantityDTO(500, "MILLILITRE", "VolumeUnit")); // 1L * 0.5L = 0.5 L^2 approx

        QuantityMeasurementEntity saved = saveMock(new QuantityMeasurementEntity());
        QuantityMeasurementEntity result = service.multiply(input);

        assertEquals("MULTIPLY", result.getOperation());
        assertEquals(0.5, result.getResultValue(), 0.0001);
        assertEquals("LITRE", result.getResultUnit());
    }

    // ==================== DIVIDE ====================
    @Test
    void testDivide_Temperature_Celsius() {
        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(new QuantityDTO(100, "CELSIUS", "TemperatureUnit"));
        input.setThatQuantityDTO(new QuantityDTO(2, "CELSIUS", "TemperatureUnit"));

        QuantityMeasurementEntity saved = saveMock(new QuantityMeasurementEntity());
        QuantityMeasurementEntity result = service.divide(input);

        assertEquals("DIVIDE", result.getOperation());
        assertEquals(50, result.getResultValue(), 0.0001);
        assertEquals("CELSIUS", result.getResultUnit());
    }

    @Test
    void testDivideByZero() {
        QuantityInputDTO input = new QuantityInputDTO();
        input.setThisQuantityDTO(new QuantityDTO(1, "METER", "LengthUnit"));
        input.setThatQuantityDTO(new QuantityDTO(0, "CM", "LengthUnit"));

        QuantityMeasurementEntity saved = saveMock(new QuantityMeasurementEntity());
        QuantityMeasurementEntity result = service.divide(input);

        assertTrue(result.isError());
        assertEquals("Cannot divide by zero", result.getErrorMessage());
    }

    // ==================== HISTORY / COUNT ====================
    @Test
    void testHistoryAndCount() {
        QuantityMeasurementEntity e1 = new QuantityMeasurementEntity();
        e1.setOperation("ADD");
        QuantityMeasurementEntity e2 = new QuantityMeasurementEntity();
        e2.setOperation("ADD");

        when(repository.findByOperation("ADD")).thenReturn(Arrays.asList(e1, e2));
        when(repository.countByOperationAndErrorFalse("ADD")).thenReturn(2L);

        List<QuantityMeasurementEntity> history = service.getHistoryByOperation("ADD");
        long count = service.getOperationCount("ADD");

        assertEquals(2, history.size());
        assertEquals(2, count);
    }
}