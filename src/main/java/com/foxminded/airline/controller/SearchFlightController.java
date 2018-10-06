package com.foxminded.airline.controller;

import com.foxminded.airline.domain.service.impl.FlightServiceImpl;
import com.foxminded.airline.dto.FlightDTO;
import com.foxminded.airline.utils.FlightConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class SearchFlightController {
    @Autowired
    FlightServiceImpl flightService;

    private FlightDTO flightDTO;

    @GetMapping(value = "/searchflight",
            produces = MediaType.TEXT_HTML_VALUE,
            params = {"nameDepartureAirport", "nameArrivalAirport", "date"})
    public String showBuyTicket(@RequestParam("nameDepartureAirport") String nameDepartureAirport,
                                @RequestParam("nameArrivalAirport") String nameArrivalAirport,
                                @RequestParam("date") String date) {
        flightDTO = new FlightDTO();
        flightDTO.setDepartureAirport(nameDepartureAirport);
        flightDTO.setArrivalAirport(nameArrivalAirport);
        flightDTO.setDateString(date);
        return "searchFlight";
    }

    @PostMapping("/searchflight")
    public ResponseEntity<List<FlightDTO>> searchFlight() throws IOException {
        return new ResponseEntity<>(new FlightConverter().createDTOsForFlights(flightService.findFlightByFlightDTO(flightDTO)), HttpStatus.OK);
    }
}