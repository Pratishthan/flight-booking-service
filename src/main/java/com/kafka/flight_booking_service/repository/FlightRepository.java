package com.kafka.flight_booking_service.repository;

import com.kafka.flight_booking_service.model.Booking;
import com.kafka.flight_booking_service.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> findBySourceAndDestination(String source, String destination);

    Optional<Flight> findByFlightNumber(String flightNumber);
}
