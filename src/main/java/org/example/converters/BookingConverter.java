package org.example.converters;

import org.example.dao.entity.BookingEntity;
import org.example.dao.repository.DeviceRepository;
import org.example.dao.repository.EmployeeRepository;
import org.example.models.MobileBooking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BookingConverter {
    private static final Logger logger = LoggerFactory.getLogger(BookingConverter.class);

    private final EmployeeRepository employeeRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public BookingConverter(EmployeeRepository employeeRepository, DeviceRepository deviceRepository) {
        this.employeeRepository = employeeRepository;
        this.deviceRepository = deviceRepository;
    }

    public BookingEntity convertToEntity(MobileBooking mobileBooking) {
        if (mobileBooking == null) {
            return null;
        }

        logger.info("Converting MobileBooking to BookingEntity...");
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookedAt(LocalDateTime.now());
        bookingEntity.setDeviceEntity(deviceRepository.getReferenceById(mobileBooking.getDeviceId()));
        bookingEntity.setEmployeeEntity(employeeRepository.getReferenceById(mobileBooking.getEmployeeId()));

        logger.info("Conversion completed.");
        return bookingEntity;
    }
}