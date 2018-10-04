package com.foxminded.airline.controller;

import com.foxminded.airline.domain.service.AirportService;
import com.foxminded.airline.dto.AirportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    AirportService airportService;

    @RequestMapping(value = "/", produces =
            MediaType.TEXT_HTML_VALUE,
            method = RequestMethod.GET)
    public String show() {
        return "index";
    }

    @RequestMapping(value = "/searchAirport", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirportDTO>> searchAirport(@RequestBody AirportDTO airportDTO) {
        return new ResponseEntity<List<AirportDTO>>(airportService.createDTOsForAirports(airportService.findAirportsByNamePart(airportDTO.getName())), HttpStatus.OK);
    }
}
