package org.example.interfaces;

import java.util.List;

public interface DeviceManagerService {
    List<Device> addDevice(Device device);
    List<Device> removeDevice(Device device);
    List<Device> listAllDevice();
}
