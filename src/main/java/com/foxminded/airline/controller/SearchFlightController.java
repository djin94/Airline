package com.foxminded.airline.controller;

import com.foxminded.airline.dao.FlightDAO;
import com.foxminded.airline.domain.Flight;
import com.foxminded.airline.dto.FlightDTO;
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
public class SearchFlightController {

    @RequestMapping(value = "/searchflight",
            produces = MediaType.TEXT_HTML_VALUE,
            params = {"nameDepartureAirport", "nameArrivalAirport"},
            method = RequestMethod.GET)
    public String showBuyTicket() {
        return "searchFlight";
    }

    @RequestMapping(value = "/searchflight",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<List<FlightDTO>> searchFlight() throws IOException {
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
}
