package org.example.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "employee")
@Entity
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String key;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column
    private String address;
}

