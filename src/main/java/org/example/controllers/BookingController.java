package org.example.controllers;

import org.example.models.MobileBooking;
import org.example.services.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/booking")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<String> booking(@RequestBody MobileBooking mobileBooking) {
        String responseString = "";
        if (mobileBooking.getAction() == MobileBooking.Action.BOOK) {
            logger.info("Received a booking request: {}", mobileBooking);
            responseString = bookingService.book(mobileBooking);
            logger.info("Booking response: {}", responseString);
        } else if (mobileBooking.getAction() == MobileBooking.Action.RETURN) {
            logger.info("Received a return request: {}", mobileBooking);
            responseString = bookingService._return(mobileBooking);
            logger.info("Return response: {}", responseString);
        }

        return ResponseEntity.ok(responseString);
    }
}