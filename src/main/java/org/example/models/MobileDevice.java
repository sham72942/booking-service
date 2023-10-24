package org.example.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MobileDevice {
    private Integer id;
    private String imei;
    private String model;
    private Boolean available;
    private String description;
    private LocalDateTime bookedAt;
    private Integer bookedBy;
}
