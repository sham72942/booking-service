package org.example.services;

import org.example.models.MobileBooking;

public interface BookingService {
    public String book(MobileBooking booking);
    public String _return(MobileBooking booking);
}
