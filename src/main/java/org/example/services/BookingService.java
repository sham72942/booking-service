package org.example.services;

import org.example.models.MobileBooking;

public interface BookingService {
    public String bookDevice(MobileBooking booking);
    public String returnDevice(MobileBooking booking);
}
