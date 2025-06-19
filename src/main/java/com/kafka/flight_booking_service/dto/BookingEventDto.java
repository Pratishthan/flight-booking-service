package com.kafka.flight_booking_service.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class BookingEventDto {
    private Long id;
    private String passengerName;
    private int seatsBooked;
    private String status;
    private double bookedCost;
    private String flightNumber;
    private String flightName;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime bookingTime;
    private String email;

    public BookingEventDto(Long id, String passengerName, int seatsBooked, String status, double bookedCost, String flightNumber, String flightName, String source, String destination, LocalDateTime departureTime, LocalDateTime bookingTime,String email) {
        this.id = id;
        this.passengerName = passengerName;
        this.seatsBooked = seatsBooked;
        this.status = status;
        this.bookedCost = bookedCost;
        this.flightNumber = flightNumber;
        this.flightName = flightName;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.bookingTime = bookingTime;
        this.email=email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBookedCost() {
        return bookedCost;
    }

    public void setBookedCost(double bookedCost) {
        this.bookedCost = bookedCost;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
