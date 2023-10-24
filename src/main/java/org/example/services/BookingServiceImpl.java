package org.example.services;

import org.example.converters.BookingConverter;
import org.example.dao.entity.BookingEntity;
import org.example.dao.repository.BookingRepository;
import org.example.dao.repository.DeviceLockRepository;
import org.example.dao.repository.DeviceRepository;
import org.example.models.MobileBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final DeviceRepository deviceRepository;
    private final BookingConverter bookingConverter;
    private final DeviceLockRepository deviceLockRepository; // Inject your DeviceLockRepository

    @Autowired
    public BookingServiceImpl(
            BookingRepository bookingRepository,
            DeviceRepository deviceRepository,
            BookingConverter bookingConverter,
            DeviceLockRepository deviceLockRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.deviceRepository = deviceRepository;
        this.bookingConverter = bookingConverter;
        this.deviceLockRepository = deviceLockRepository;
    }


    @Transactional
    public String book(MobileBooking mobileBooking) {
        BookingEntity bookingEntity = bookingConverter.convertToEntity(mobileBooking);
        //Check if device is available
        if(!deviceRepository.existsDeviceByIdAndAvailableTrue(mobileBooking.getDeviceId()))
            return "Impossible to book this device";
        //create a db transaction
        //Take lock on device is available on device table
        deviceLockRepository.lockDeviceByIdAndAvailable(mobileBooking.getDeviceId(), true);
       //Insert into table mobileBooking
        bookingRepository.save(bookingEntity);
        deviceRepository.updateDeviceAvailability(mobileBooking.getDeviceId(), false);
        return "Device booked successfully.";
    }

    @Transactional
    public String _return(MobileBooking mobileBooking) {
        if (!deviceRepository.existsDeviceByIdAndAvailableFalse(mobileBooking.getDeviceId())) {
            return "Impossible to return this device";
        }

        deviceLockRepository.lockDeviceByIdAndAvailable(mobileBooking.getDeviceId(), false);

        int updatedRowCount = bookingRepository.updateReturnedAtForDeviceAndEmployee(
                mobileBooking.getDeviceId(),
                mobileBooking.getEmployeeId()
        );

        if (updatedRowCount == 0) {
            return "No current mobileBooking found for this device and employee.";
        }

        deviceRepository.updateDeviceAvailability(mobileBooking.getDeviceId(), true);

        return "Device returned successfully.";
    }
}
