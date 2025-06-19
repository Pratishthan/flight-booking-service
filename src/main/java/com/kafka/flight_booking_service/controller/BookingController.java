package com.kafka.flight_booking_service.controller;

import com.kafka.flight_booking_service.model.Booking;
import com.kafka.flight_booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/flight/{flightId}")
    public ResponseEntity<Booking> bookFlight(@PathVariable long flightId,
                                              @RequestParam String passengerName,
                                              @RequestParam String email,
                                              @RequestParam int seats){
        Booking booking=bookingService.bookFlight(flightId,passengerName,email,seats);
        return ResponseEntity.ok(booking);
    }
}
