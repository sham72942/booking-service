package org.example.dao.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "booking")
@Entity
@Data
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id", nullable = false)
    private DeviceEntity deviceEntity;

    @Column(nullable = false)
    private LocalDateTime bookedAt;

    @Column
    private LocalDateTime returnedAt;

    @ManyToOne
    @JoinColumn(name = "booked_by", referencedColumnName = "id", nullable = false)
    private EmployeeEntity employeeEntity;
}

