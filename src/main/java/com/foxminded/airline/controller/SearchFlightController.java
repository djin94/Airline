package com.foxminded.airline.controller;

import com.foxminded.airline.dao.AirportDAO;
import com.foxminded.airline.dao.FlightDAO;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchFlightController {
    @Autowired
    DataSource dataSource;

    @Autowired
    FlightDAO flightDAO;

    @Autowired
    AirportDAO airportDAO;

    private FlightDTO flightDTO;

    @RequestMapping(value = "/searchflight",
            produces = MediaType.TEXT_HTML_VALUE,
            params = {"nameDepartureAirport", "nameArrivalAirport", "date"},
            method = RequestMethod.GET)
    public String showBuyTicket(@RequestParam("nameDepartureAirport") String nameDepartureAirport,
                                @RequestParam("nameArrivalAirport") String nameArrivalAirport,
                                @RequestParam("date") String date) {
        flightDTO = new FlightDTO();
        flightDTO.setDepartureAirport(nameDepartureAirport);
        flightDTO.setArrivalAirport(nameArrivalAirport);
        flightDTO.setDateString(date);
        return "searchFlight";
    }

    @RequestMapping("/searchflight")
    public String showAllFlights() {
        return "searchFlight";
    }

    @Transactional
    @RequestMapping(value = "/searchflight",
            method = RequestMethod.POST)
    public ResponseEntity<List<FlightDTO>> searchFlight() throws IOException {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Airport departureAirport = airportDAO.findByNameIgnoreCase(flightDTO.getDepartureAirport());
        Airport arrivalAirport = airportDAO.findByNameIgnoreCase(flightDTO.getArrivalAirport());
        LocalDate dateFlight = LocalDate.parse(flightDTO.getDateString(), dateFormat);
        List<Flight> flights = flightDAO.findByDepartureAirportAndArrivalAirportAndDate(departureAirport, arrivalAirport, dateFlight);
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
        return new ResponseEntity<List<FlightDTO>>(flightDTOS, HttpStatus.OK);
    }
}