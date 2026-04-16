package com.spring.history.controller;

import com.spring.history.entity.HistoryEntity;
import com.spring.history.service.HistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "History APIs", description = "User operation history management")
@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService service;

    // SAVE HISTORY
    @Operation(summary = "Save operation history")
    @PostMapping
    public HistoryEntity save(@RequestBody HistoryEntity history) {
        return service.save(history);
    }

    // USER HISTORY
    @Operation(summary = "Get history by userId")
    @GetMapping("/user/{userId}")
    public List<HistoryEntity> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    // OPERATION HISTORY
    @Operation(summary = "Get history by operation type")
    @GetMapping("/operation/{operation}")
    public List<HistoryEntity> getByOperation(@PathVariable String operation) {
        return service.getByOperation(operation);
    }
}