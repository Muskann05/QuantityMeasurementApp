package com.quantity.app;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.dto.QuantityDTO;
import com.quantity.repository.QuantityMeasurementCacheRepository;
import com.quantity.service.IQuantityMeasurementService;
import com.quantity.service.QuantityMeasurementServiceImpl;

public class App {

    public static void main(String[] args) {

        QuantityMeasurementCacheRepository repo =
                QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCH");

        controller.performComparison(q1, q2);

        controller.performAddition(q1, q2);

        controller.performConversion(q1,"INCH");
    }
}