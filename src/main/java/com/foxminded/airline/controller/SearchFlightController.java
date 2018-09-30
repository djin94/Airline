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
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchFlightController {
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

    @RequestMapping(value = "/searchflight",
            method = RequestMethod.POST)
    public ResponseEntity<List<FlightDTO>> searchFlight() throws IOException {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        FlightDAO flightDAO = new FlightDAO();
        List<Flight> flights = flightDAO.getAll();
        List<FlightDTO> flightDTOS = new ArrayList<>();
        flights.stream()
                .filter(flight -> flight.getDepartureAirport().getName().toLowerCase().equals(flightDTO.getDepartureAirport().toLowerCase()) &&
                        flight.getArrivalAirport().getName().toLowerCase().equals(flightDTO.getArrivalAirport().toLowerCase()) &&
                        flight.getDate().toLocalDate().equals(LocalDate.parse(flightDTO.getDateString(), dateFormat)))
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