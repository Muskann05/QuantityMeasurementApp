package com.spring.measurement.controller;

import com.spring.measurement.dto.QuantityInputDTO;
import com.spring.measurement.dto.QuantityResponseDTO;
import com.spring.measurement.service.IQuantityMeasurementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Measurement APIs", description = "All quantity measurement operations")
@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    // COMPARE
    @Operation(summary = "Compare two quantities")
    @PostMapping("/compare")
    public QuantityResponseDTO compare(@RequestBody QuantityInputDTO input) {
        return service.compare(input);
    }

    // CONVERT
    @Operation(summary = "Convert quantity from one unit to another")
    @PostMapping("/convert")
    public QuantityResponseDTO convert(@RequestBody QuantityInputDTO input) {
        return service.convert(input);
    }

    // ADD
    @Operation(summary = "Add two quantities")
    @PostMapping("/add")
    public QuantityResponseDTO add(@RequestBody QuantityInputDTO input) {
        return service.add(input);
    }

    // SUBTRACT
    @Operation(summary = "Subtract two quantities")
    @PostMapping("/subtract")
    public QuantityResponseDTO subtract(@RequestBody QuantityInputDTO input) {
        return service.subtract(input);
    }

    // MULTIPLY
    @Operation(summary = "Multiply two quantities")
    @PostMapping("/multiply")
    public QuantityResponseDTO multiply(@RequestBody QuantityInputDTO input) {
        return service.multiply(input);
    }

    // DIVIDE
    @Operation(summary = "Divide two quantities")
    @PostMapping("/divide")
    public QuantityResponseDTO divide(@RequestBody QuantityInputDTO input) {
        return service.divide(input);
    }
}