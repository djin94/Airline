package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.dao.AirportDAO;
import com.foxminded.airline.dao.FlightDAO;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("flightService")
public class FlightServiceImpl implements FlightService {
    @Autowired
    DataSource dataSource;

    @Autowired
    AirportDAO airportDAO;

    @Autowired
    FlightDAO flightDAO;

    @Override
    public FlightDTO createFlightDTO(String nameDepartureAirport, String nameArrivalAirport, String date) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setDepartureAirport(nameDepartureAirport);
        flightDTO.setArrivalAirport(nameArrivalAirport);
        flightDTO.setDateString(date);
        return flightDTO;
    }

    @Transactional
    @Override
    public List<Flight> findFlightByFlightDTO(FlightDTO flightDTO) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Airport departureAirport = airportDAO.findByNameIgnoreCase(flightDTO.getDepartureAirport());
        Airport arrivalAirport = airportDAO.findByNameIgnoreCase(flightDTO.getArrivalAirport());
        LocalDate dateFlight = LocalDate.parse(flightDTO.getDateString(), dateFormat);
        List<Flight> flights = flightDAO.findByDepartureAirportAndArrivalAirportAndDate(departureAirport, arrivalAirport, dateFlight);
        return flights;
    }

    @Override
    public List<FlightDTO> createDTOsForFlights(List<Flight> flights) {
        List<FlightDTO> flightDTOS = new ArrayList<>();
        flights.stream()
                .forEach(flight -> {
                    FlightDTO flightDTO = new FlightDTO();
                    flightDTO.setNumber(flight.getNumber());
                    flightDTO.setPlaneName(flight.getPlane().getName());
                    flightDTO.setDateString(flight.getDate().toString());
                    flightDTO.setTimeString(flight.getTime().toString());
                    flightDTO.setDepartureAirport(flight.getDepartureAirport().getName());
                    flightDTO.setArrivalAirport(flight.getArrivalAirport().getName());
                    flightDTOS.add(flightDTO);
                });
        return flightDTOS;
    }
}
