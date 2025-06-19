package com.kafka.flight_booking_service.service;

import com.kafka.flight_booking_service.enums.BookingStatus;
import com.kafka.flight_booking_service.exceptions.ResourceNotFoundException;
import com.kafka.flight_booking_service.kafkaService.KafkaProducerService;
import com.kafka.flight_booking_service.model.Booking;
import com.kafka.flight_booking_service.model.Flight;
import com.kafka.flight_booking_service.repository.BookingRepository;
import com.kafka.flight_booking_service.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public Booking bookFlight(Long flightId, String passengerName,String email, int seatsToBook) {
        Optional<Flight> optionalFlight=flightRepository.findById(flightId);
        if(optionalFlight.isEmpty()){
            throw new ResourceNotFoundException("Flight not found for id :"+flightId);
        }
        Flight flight=optionalFlight.get();
        if(flight.getAvailableSeats()<seatsToBook){
            throw new IllegalArgumentException("Only " + flight.getAvailableSeats() + " seats available");
        }
        flight.setAvailableSeats(flight.getAvailableSeats()-seatsToBook);
        flightRepository.save(flight);


        // Create booking
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setPassengerName(passengerName);
        booking.setSeatsBooked(seatsToBook);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setBookedCost(flight.getCost()*seatsToBook);
        booking.setDepartureTime(flight.getDepartureTime());
        booking.setDestination(flight.getDestination());
        booking.setFlightName(flight.getName());
        booking.setFlightNumber(flight.getFlightNumber());
        booking.setSource(flight.getSource());
        booking.setEmail(email);

        Booking saveBooking= bookingRepository.save(booking);
        kafkaProducerService.sendBookingEvent(saveBooking);
        return saveBooking;
    }

}
