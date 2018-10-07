package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.service.impl.FlightServiceImpl;
import com.foxminded.airline.dto.FlightDTO;
import com.foxminded.airline.utils.FlightConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {
    private FlightDTO flightDTO;

    @Autowired
    FlightServiceImpl flightService;

    @Autowired
    FlightConverter flightConverter;

    @GetMapping("/admin")
    public String showAdminPage(){
        return "admin/admin";
    }

    @GetMapping("/admin/searchflights")
    public String showSearchFlights(){
        return "admin/searchFlights";
    }

    @GetMapping(value = "/admin/listflights",
            produces = MediaType.TEXT_HTML_VALUE,
            params = {"nameAirport", "date"})
    public String showListFlights(@RequestParam("nameAirport") String nameAirport,
                                @RequestParam("date") String date) {
        flightDTO = new FlightDTO();
        flightDTO.setDepartureAirport(nameAirport);
        flightDTO.setArrivalAirport("");
        flightDTO.setDateString(date);
        return "admin/listFlights";
    }

    @PostMapping("/searchflight")
    public ResponseEntity<List<FlightDTO>> searchFlight() {
        return new ResponseEntity<>(flightConverter.createDTOsForFlights(flightService.findFlightsForAirportByDate(flightDTO)), HttpStatus.OK);
    }
}
