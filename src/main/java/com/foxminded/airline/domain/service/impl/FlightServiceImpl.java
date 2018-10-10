package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.web.dao.AirportRepository;
import com.foxminded.airline.web.dao.FlightRepository;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.dto.FlightDTO;
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
    public Flight findFlightByNumberAndDateAndTime(String number, LocalDate date, LocalTime time) {
        return flightRepository.findByNumberAndDateAndTime(number, date, time).get();
    }

    @Override
    public List<Flight> findFlightsByFlightDTO(FlightDTO flightDTO) {
        LocalDate dateFlight = LocalDate.parse(flightDTO.getDateString());
        Airport departureAirport = airportRepository.findByNameIgnoreCase(flightDTO.getDepartureAirport()).get();
        Airport arrivalAirport = airportRepository.findByNameIgnoreCase(flightDTO.getArrivalAirport()).get();
        List<Flight> flights =new ArrayList<>();
        flights.addAll(flightRepository.findByDepartureAirportAndArrivalAirportAndDate(departureAirport, arrivalAirport, dateFlight));
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDate(departureAirport, arrivalAirport, dateFlight);
    }

    @Override
    public List<Flight> findFlightsForAirportByDate(FlightDTO flightDTO) {
        LocalDate dateFlight = LocalDate.parse(flightDTO.getDateString());
        Airport airport = airportRepository.findByNameIgnoreCase(flightDTO.getDepartureAirport()).get();
        List<Flight> flights = flightRepository.findByDepartureAirportAndDate(airport, dateFlight);
        flights.addAll(flightRepository.findByArrivalAirportAndDate(airport, dateFlight));
        return flights;
    }
}
