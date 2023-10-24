package org.example.dao.repository;

import org.example.dao.entity.DeviceEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {
    @Override
    List<DeviceEntity> findAll();

    @Override
    Optional<DeviceEntity> findById(Integer integer);
    @Modifying
    @Transactional
    @Query("UPDATE DeviceEntity d SET d.available = ?2 WHERE d.id = ?1")
    void updateDeviceAvailability(Integer deviceId, boolean isAvailable);

    Boolean existsDeviceByIdAndAvailableTrue(Integer deviceId);
    Boolean existsDeviceByIdAndAvailableFalse(Integer deviceId);
}

