package org.example.converters;

import org.example.dao.entity.DeviceEntity;
import org.example.models.MobileDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeviceConverter {
    private static final Logger logger = LoggerFactory.getLogger(DeviceConverter.class);

    public MobileDevice convertToMobileDevice(DeviceEntity deviceEntity){
        if (deviceEntity == null) {
            logger.warn("Null DeviceEntity provided for conversion.");
            return null;
        }

        logger.info("Converting DeviceEntity to MobileDevice...");

        MobileDevice mobileDevice = new MobileDevice();
        mobileDevice.setId(deviceEntity.getId());
        mobileDevice.setImei(deviceEntity.getImei());
        mobileDevice.setModel(deviceEntity.getModel());
        mobileDevice.setAvailable(deviceEntity.getAvailable());
        mobileDevice.setDescription(deviceEntity.getDescription());

        logger.info("Conversion completed.");
        return mobileDevice;
    }
}