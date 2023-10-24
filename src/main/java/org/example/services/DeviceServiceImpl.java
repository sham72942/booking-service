package org.example.services;

import org.example.controllers.DeviceController;
import org.example.converters.DeviceConverter;
import org.example.dao.entity.BookingEntity;
import org.example.dao.entity.DeviceEntity;
import org.example.dao.repository.BookingRepository;
import org.example.dao.repository.DeviceRepository;
import org.example.models.MobileDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    private final DeviceRepository deviceRepository;
    private final BookingRepository bookingRepository;
    private final DeviceConverter deviceConverter;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, BookingRepository bookingRepository,
                             DeviceConverter deviceConverter) {
        this.deviceRepository = deviceRepository;
        this.bookingRepository = bookingRepository;
        this.deviceConverter = deviceConverter;
    }

    @Override
    public MobileDevice getDeviceById(int id) {
        Optional<DeviceEntity> deviceEntityOptional = deviceRepository.findById(id);
        if (deviceEntityOptional.isPresent()) {
            DeviceEntity deviceEntity = deviceEntityOptional.get();
            MobileDevice mobileDevice = deviceConverter.convertToMobileDevice(deviceEntity);
            BookingEntity latestBooking = bookingRepository.findTopByDeviceEntityOrderByBookedAtDesc(deviceEntity);

            if (latestBooking != null) {
                mobileDevice.setBookedAt(latestBooking.getBookedAt());
                mobileDevice.setBookedBy(latestBooking.getEmployeeEntity().getId());
            }

            return mobileDevice;
        }
        return null;
    }

    @Override
    public List<MobileDevice> getAllDevices() {
        List<MobileDevice> mobileDevices = new ArrayList<>();

        List<DeviceEntity> deviceEntities = deviceRepository.findAll();
        logger.info("found deviceEntities: {}", deviceEntities);

        for (DeviceEntity deviceEntity : deviceEntities) {
            MobileDevice mobileDevice = deviceConverter.convertToMobileDevice(deviceEntity);
            logger.info("mobile device: {}", mobileDevice);

            // Fetch the latest booking for the device
            BookingEntity latestBooking = bookingRepository.findTopByDeviceEntityOrderByBookedAtDesc(deviceEntity);

            if (latestBooking != null) {
                mobileDevice.setBookedAt(latestBooking.getBookedAt());
                mobileDevice.setBookedBy(latestBooking.getEmployeeEntity().getId());
            }

            mobileDevices.add(mobileDevice);
        }

        return mobileDevices;
    }
}
