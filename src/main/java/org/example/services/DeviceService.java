package org.example.services;

import org.example.models.MobileDevice;

import java.util.List;

public interface DeviceService {
    public MobileDevice getDeviceById(int id);
    public List<MobileDevice> getAllDevices();
}
