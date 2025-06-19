package com.kafka.flight_booking_service.service;

import com.kafka.flight_booking_service.exceptions.ResourceNotFoundException;
import com.kafka.flight_booking_service.model.Flight;
import com.kafka.flight_booking_service.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Flight addFlight(Flight flight){
        return flightRepository.save(flight);
    }

    public List<Flight> getFlightBySourceAndDestination(String source, String destination){
        List<Flight> flightList=flightRepository.findBySourceAndDestination(source,destination);
        if(flightList.isEmpty()){
            throw new ResourceNotFoundException("Flight not found with the given source and destination");
        }
        return flightList;
    }

    public List<Flight> getAllFlights(){
        List<Flight>  flightList= flightRepository.findAll();
        if(flightList.isEmpty()){
            throw new ResourceNotFoundException("No flights are added");
        }
        return flightList;
    }

    public Flight getByFlightNumber(String flightNumber){
        Optional<Flight> flightDetails=flightRepository.findByFlightNumber(flightNumber);
        if(flightDetails.isPresent()){
            return flightDetails.get();
        }
        else{
            throw new ResourceNotFoundException("Flight not found with number: " + flightNumber);
        }
    }

}
