package org.example.services;

import org.example.converters.BookingConverter;
import org.example.dao.entity.BookingEntity;
import org.example.dao.entity.DeviceEntity;
import org.example.dao.repository.BookingRepository;
import org.example.dao.repository.DeviceRepository;
import org.example.exceptions.EntityNotFound;
import org.example.models.MobileBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final DeviceRepository deviceRepository;
    private final BookingConverter bookingConverter;

    @Autowired
    public BookingServiceImpl(
            BookingRepository bookingRepository,
            DeviceRepository deviceRepository,
            BookingConverter bookingConverter
    ) {
        this.bookingRepository = bookingRepository;
        this.deviceRepository = deviceRepository;
        this.bookingConverter = bookingConverter;
    }

    @Transactional
    public String bookDevice(MobileBooking mobileBooking) {
        BookingEntity bookingEntity = bookingConverter.convertToEntity(mobileBooking);

        DeviceEntity deviceEntity = deviceRepository.lockDeviceById(mobileBooking.getDeviceId())
                .orElseThrow(() -> new EntityNotFound("Device is unavailable for booking"));

        bookingRepository.save(bookingEntity);
        deviceEntity.setAvailable(false);
        deviceRepository.save(deviceEntity);
        return "Device booked successfully.";
    }


    @Transactional
    public String returnDevice(MobileBooking mobileBooking) {

        BookingEntity bookingEntity = bookingRepository.findByDeviceIdAndReturnedAtIsNull(mobileBooking.getDeviceId())
                .orElseThrow(() -> new EntityNotFound("No current mobileBooking found for this device"));
        DeviceEntity deviceEntity = deviceRepository.findById(mobileBooking.getDeviceId())
                .orElseThrow(() -> new EntityNotFound("Device not found"));

        bookingEntity.setReturnedAt(LocalDateTime.now());
        bookingRepository.save(bookingEntity);

        deviceEntity.setAvailable(true);
        deviceRepository.save(deviceEntity);

        return "Device returned successfully.";
    }
}
