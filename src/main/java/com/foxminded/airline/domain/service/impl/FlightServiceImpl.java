package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.web.dao.AirportRepository;
import com.foxminded.airline.web.dao.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        Airport departureAirport = airportRepository.findByNameIgnoreCase(departureAirportName).get();
        Airport arrivalAirport = airportRepository.findByNameIgnoreCase(arrivalAirportName).get();
        List<Flight> flights = new ArrayList<>();
        flights.addAll(flightRepository.findByDepartureAirportAndArrivalAirportAndDate(departureAirport, arrivalAirport, dateFlight));
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDate(departureAirport, arrivalAirport, dateFlight);
    }

    @Override
    public List<Flight> findFlightsForAirportByDate(String dateString, String airportName) {
        LocalDate dateFlight = LocalDate.parse(dateString);
        Airport airport = airportRepository.findByNameIgnoreCase(airportName).get();
        List<Flight> flights = flightRepository.findByDepartureAirportAndDate(airport, dateFlight);
        flights.addAll(flightRepository.findByArrivalAirportAndDate(airport, dateFlight));
        return flights;
    }
}
