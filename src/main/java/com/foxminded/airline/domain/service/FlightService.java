package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    List<Flight> searchFlightsByAirportsAndDate(Airport departureAirport, Airport arrivalAirport, LocalDate date);
}
