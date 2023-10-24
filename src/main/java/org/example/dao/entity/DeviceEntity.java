package org.example.dao.entity;

import lombok.Data;

import javax.persistence.*;


@Table(name = "device")
@Entity
@Data
public class DeviceEntity {
    @Id
    private Integer id;

    @Column(unique = true)
    private String imei;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Boolean available;

    @Column
    private String description;
}

