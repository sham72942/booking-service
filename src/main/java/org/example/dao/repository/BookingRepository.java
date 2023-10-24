package org.example.dao.repository;

import org.example.dao.entity.BookingEntity;
import org.example.dao.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

    @Modifying
    @Query("UPDATE BookingEntity db " +
            "SET db.returnedAt = CURRENT_TIMESTAMP " +
            "WHERE db.deviceEntity.id = ?1 AND db.employeeEntity.id = ?2 AND db.returnedAt IS NULL")
    Integer updateReturnedAtForDeviceAndEmployee(Integer deviceId, Integer bookedBy);

    @Override
    BookingEntity save(BookingEntity s);

    BookingEntity findTopByDeviceEntityOrderByBookedAtDesc(DeviceEntity deviceEntity);
}

