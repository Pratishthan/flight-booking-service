package com.kafka.flight_booking_service.kafkaService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kafka.flight_booking_service.dto.BookingEventDto;
import com.kafka.flight_booking_service.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    private final String TOPIC="bookings";

    public void sendBookingEvent(Booking booking){
        BookingEventDto event = new BookingEventDto(
                booking.getBookingId(),
                booking.getPassengerName(),
                booking.getSeatsBooked(),
                booking.getStatus().toString(),
                booking.getBookedCost(),
                booking.getFlight().getFlightNumber(),
                booking.getFlightName(),
                booking.getSource(),
                booking.getDestination(),
                booking.getDepartureTime(),
                booking.getBookingTime(),
                booking.getEmail()


        );
       try {
           String bookingJson= objectMapper.writeValueAsString(event);
           kafkaTemplate.send(TOPIC,bookingJson);
       } catch (JsonProcessingException e) {
           throw new RuntimeException("Failed to serialize booking", e);
       }
    }
}
