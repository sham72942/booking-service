package org.example.controllers;

import org.example.models.MobileDevice;
import org.example.services.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/devices")
public class DeviceController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MobileDevice> getDeviceById(@PathVariable Integer id) {
        logger.info("Fetching device by id: {}", id);
        try {
            MobileDevice device = deviceService.getDeviceById(id);
            if (device != null) {
                return ResponseEntity.ok(device);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error retrieving device by ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MobileDevice>> getAllDevices() {
        logger.info("Fetching all devices");
        try {
            List<MobileDevice> devices = deviceService.getAllDevices();
            if (!devices.isEmpty()) {
                return ResponseEntity.ok(devices);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            logger.error("Error retrieving all devices: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}