package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.web.dao.AirportRepository;
import com.foxminded.airline.web.dao.FlightRepository;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("flightService")
public class FlightServiceImpl implements FlightService {
    @Autowired
    DataSource dataSource;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    FlightRepository flightRepository;

    @Transactional
    @Override
    public Flight findFlightByNumberAndDateAndTime(String number, LocalDate date, LocalTime time) {
        return flightRepository.findByNumberAndDateAndTime(number, date, time).get();
    }

    @Transactional
    @Override
    public List<Flight> findFlightByFlightDTO(FlightDTO flightDTO) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFlight = LocalDate.parse(flightDTO.getDateString(), dateFormat);
        Airport departureAirport = airportRepository.findByNameIgnoreCase(flightDTO.getDepartureAirport()).get();
        Airport arrivalAirport = airportRepository.findByNameIgnoreCase(flightDTO.getArrivalAirport()).get();
        return flightRepository.findByDepartureAirportAndArrivalAirportAndDate(departureAirport, arrivalAirport, dateFlight);
    }

    @Override
    public List<Flight> findFlightsForAirportByDate(FlightDTO flightDTO) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFlight = LocalDate.parse(flightDTO.getDateString(), dateFormat);
        Airport airport = airportRepository.findByNameIgnoreCase(flightDTO.getDepartureAirport()).get();
        List<Flight> flights = flightRepository.findByDepartureAirportAndDate(airport, dateFlight);
        flights.addAll(flightRepository.findByArrivalAirportAndDate(airport, dateFlight));
        return flights;
    }
}
