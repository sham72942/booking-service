package org.example.interfaces;

public interface BookingManagerService {

    public boolean bookDevice(Device device, Person person);

    public boolean returnDevice(Device device);
}
