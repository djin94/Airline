package com.foxminded.airline.controller;

import com.foxminded.airline.dao.AirportDAO;
import com.foxminded.airline.dao.FlightDAO;
import com.foxminded.airline.domain.Airport;
import com.foxminded.airline.domain.Flight;
import com.foxminded.airline.dto.AirportDTO;
import com.foxminded.airline.dto.FlightDTO;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {
    @RequestMapping(value = "/", produces =
            MediaType.TEXT_HTML_VALUE,
            method = RequestMethod.GET)
    public String show() {
        return "index";
    }

    @RequestMapping(value = "/", produces =
            MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public ResponseEntity<List<FlightDTO>> showFlight() throws IOException {
        FlightDAO flightDAO = new FlightDAO();
        List<Flight> flights = flightDAO.getAll();
        List<FlightDTO> flightDTOS = new ArrayList<>();
        flights.stream()
                .forEach(flight -> {
                    FlightDTO flightDTO = new FlightDTO();
                    flightDTO.setNumber(flight.getNumber());
                    flightDTO.setPlaneName(flight.getPlane().getName());
                    flightDTO.setDateString(flight.getDate().toLocalDate().toString());
                    flightDTO.setTimeString(flight.getDate().toLocalTime().toString());
                    flightDTO.setDepartureAirport(flight.getDepartureAirport().getName());
                    flightDTO.setArrivalAirport(flight.getArrivalAirport().getName());
                    flightDTOS.add(flightDTO);
                });
        return new ResponseEntity<List<FlightDTO>>(flightDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchAirport", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirportDTO>> searchAirport(@RequestBody AirportDTO airportDTO) {
        List<AirportDTO> suitableAirports = new ArrayList<>();
        if(airportDTO.getName()!=null){
        AirportDAO airportDAO = new AirportDAO();
        List<Airport> allAirports = airportDAO.getAll();
                allAirports.stream()
                .filter(airport -> airport.getName().toLowerCase().contains(airportDTO.getName()))
                        .forEach(airport -> {
                            AirportDTO suitableAirport = new AirportDTO();
                            suitableAirport.setName(airport.getName());
                            suitableAirports.add(suitableAirport);
                        });}
        return new ResponseEntity<List<AirportDTO>>(suitableAirports, HttpStatus.OK);
    }
}
