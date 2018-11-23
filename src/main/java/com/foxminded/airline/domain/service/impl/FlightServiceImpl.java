package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.dao.repository.AirportRepository;
import com.foxminded.airline.dao.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    AirportRepository airportRepository;

    @Autowired
    FlightRepository flightRepository;

    @Override
    public Flight findFlightByNumberAndDateAndTime(String number, String dateString, String timeString) {
        return flightRepository.findByNumberAndDateAndTime(number, LocalDate.parse(dateString), LocalTime.parse(timeString)).get();
    }

    @Override
    public List<Flight> findFlightsByDepartureAirportAndArrivalAirportAndDate(String dateString, String departureAirportName, String arrivalAirportName) {
        LocalDate dateFlight = LocalDate.parse(dateString);
        Optional<Airport> departureAirport = airportRepository.findByNameIgnoreCase(departureAirportName);
        Optional<Airport> arrivalAirport = airportRepository.findByNameIgnoreCase(arrivalAirportName);
        List<Flight> flights = new ArrayList<>();
        if (departureAirport.isPresent() && arrivalAirport.isPresent()) {
            flights.addAll(flightRepository.findByDepartureAirportAndArrivalAirportAndDate(departureAirport.get(), arrivalAirport.get(), dateFlight));
        }
        return flights;
    }

    @Override
    public List<Flight> findFlightsForAirportByDate(String dateString, String airportName) {
        LocalDate dateFlight = LocalDate.parse(dateString);
        Optional<Airport> airport = airportRepository.findByNameIgnoreCase(airportName);
        List<Flight> flights = new ArrayList<>();
        if (airport.isPresent()) {
            flights.addAll(flightRepository.findByDepartureAirportAndDate(airport.get(), dateFlight));
            flights.addAll(flightRepository.findByArrivalAirportAndDate(airport.get(), dateFlight));
        }
        return flights;
    }
}
