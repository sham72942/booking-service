package org.example.controllers;

import org.example.exceptions.EntityNotFound;
import org.example.models.MobileBooking;
import org.example.services.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<String> booking(@RequestBody @Valid MobileBooking mobileBooking) {
        String responseString = "";
        if (mobileBooking.getAction() == MobileBooking.Action.BOOK) {
            try {
                logger.info("Received a booking request: {}", mobileBooking);
                responseString = bookingService.bookDevice(mobileBooking);
                logger.info("Booking response: {}", responseString);
            } catch (EntityNotFound e) {
                logger.info("Booking failed: {}", e.getMessage());
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                logger.error("Booking failed: {}", e.getMessage());
                return ResponseEntity.internalServerError().body("Booking failed");
            }
        } else if (mobileBooking.getAction() == MobileBooking.Action.RETURN) {
            try {
                logger.info("Received a return request: {}", mobileBooking);
                responseString = bookingService.returnDevice(mobileBooking);
                logger.info("Return response: {}", responseString);
            } catch (EntityNotFound e) {
                logger.info("Return failed: {}", e.getMessage());
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                logger.error("Return failed: {}", e.getMessage());
                return ResponseEntity.internalServerError().body("Return failed");
            }
        }

        return ResponseEntity.ok(responseString);
    }
}