package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private AirportService airportService;

    @GetMapping(value = "/")
    public String showMainPage() {
        return "index";
    }

    @PostMapping(value = "/searchAirport", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airport>> searchAirport(@RequestBody Airport airport) {
        return new ResponseEntity<>(airportService.findAirportsByNamePart(airport.getName()), HttpStatus.OK);
    }
}
