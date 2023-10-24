package org.example.interfaces;

import java.util.Date;
import java.util.Optional;

public interface Device {
    boolean isAvailable();
    Optional<Date> getLastBooked();
    Optional<Person> getWhoBooked();
    Optional<Device> book(Person person);
    Device returnDevice();
}
