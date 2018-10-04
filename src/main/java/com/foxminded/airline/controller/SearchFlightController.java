package com.foxminded.airline.controller;

import com.foxminded.airline.domain.service.impl.FlightServiceImpl;
import com.foxminded.airline.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class SearchFlightController {
    @Autowired
    FlightServiceImpl flightService;

    private FlightDTO flightDTO;

    @RequestMapping(value = "/searchflight",
            produces = MediaType.TEXT_HTML_VALUE,
            params = {"nameDepartureAirport", "nameArrivalAirport", "date"},
            method = RequestMethod.GET)
    public String showBuyTicket(@RequestParam("nameDepartureAirport") String nameDepartureAirport,
                                @RequestParam("nameArrivalAirport") String nameArrivalAirport,
                                @RequestParam("date") String date) {
        flightDTO = flightService.createFlightDTO(nameDepartureAirport, nameArrivalAirport, date);
        return "searchFlight";
    }

    @RequestMapping("/searchflight")
    public String showAllFlights() {
        return "searchFlight";
    }

    @RequestMapping(value = "/searchflight",
            method = RequestMethod.POST)
    public ResponseEntity<List<FlightDTO>> searchFlight() throws IOException {
        return new ResponseEntity<List<FlightDTO>>(flightService.createDTOsForFlights(flightService.findFlightByFlightDTO(flightDTO)), HttpStatus.OK);
    }
}