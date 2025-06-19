package com.kafka.flight_booking_service.controller;

import com.kafka.flight_booking_service.model.Flight;
import com.kafka.flight_booking_service.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/flight")
@RestController
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        Flight addedFlight= flightService.addFlight(flight);
        return ResponseEntity.ok(addedFlight);
    }

    @GetMapping("/")
    public ResponseEntity<List<Flight>> findAllFlights(){
        List<Flight> flightList=flightService.getAllFlights();
          return ResponseEntity.ok(flightList);
    }

    @GetMapping("/number/{flightNumber}")
    public  ResponseEntity<Flight> findByFlightNumber(@PathVariable String flightNumber){
       Flight flight= flightService.getByFlightNumber(flightNumber);
       return ResponseEntity.ok(flight);
    }

    @GetMapping("/route/{source}/{destination}")
    public ResponseEntity<List<Flight>> findBySourceAndDestination(@PathVariable String source,@PathVariable String destination){
        List<Flight> flightList= flightService.getFlightBySourceAndDestination(source, destination);
        return ResponseEntity.ok(flightList);
    }
}
