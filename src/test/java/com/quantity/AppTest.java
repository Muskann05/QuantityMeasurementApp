package com.quantity;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.dto.QuantityDTO;
import com.quantity.repository.QuantityMeasurementDBRepository;
import com.quantity.service.IQuantityMeasurementService;
import com.quantity.service.QuantityMeasurementServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    @BeforeEach
    void setUp() {
        service = new QuantityMeasurementServiceImpl(
                new QuantityMeasurementDBRepository());
        controller = new QuantityMeasurementController(service);
    }

    // DTO Test
    @Test
    void testQuantityDTOCreation() {
        QuantityDTO dto = new QuantityDTO(10, "FEET");

        assertEquals(10.0, dto.getValue(), 0.001);
        assertEquals("FEET", dto.getUnit());
    }

    // Service Tests

    @Test
    void testCompareSameUnit() {
        QuantityDTO q1 = new QuantityDTO(10, "FEET");
        QuantityDTO q2 = new QuantityDTO(10, "FEET");

        assertTrue(service.compare(q1, q2));
    }

    @Test
    void testCompareDifferentUnit() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCH");

        assertTrue(service.compare(q1, q2));
    }


    @Test
    void testSubtraction() {
        QuantityDTO q1 = new QuantityDTO(12, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "FEET");

        QuantityDTO result = service.subtract(q1, q2);

        assertEquals(0, result.getValue());
    }

    @Test
    void testDivision() {
        QuantityDTO q1 = new QuantityDTO(10, "FEET");
        QuantityDTO q2 = new QuantityDTO(2, "FEET");

        double result = service.divide(q1, q2);

        assertEquals(5.0, result, 0.001);
    }

    @Test
    void testConversion() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET");

        QuantityDTO result = service.convert(q1, "INCH");

        assertEquals("INCH", result.getUnit());
    }

    // Controller Tests

    @Test
    void testControllerAddition() {
        QuantityDTO q1 = new QuantityDTO(5, "FEET");
        QuantityDTO q2 = new QuantityDTO(5, "FEET");

        assertDoesNotThrow(() ->
                controller.performAddition(q1, q2));
    }

    @Test
    void testControllerConversion() {
        QuantityDTO q1 = new QuantityDTO(1, "FEET");

        assertDoesNotThrow(() ->
                controller.performConversion(q1, "INCH"));
    }

}