package com.foxminded.airline.controller;

import com.foxminded.airline.dao.AirportDAO;
import com.foxminded.airline.domain.Airport;
import com.foxminded.airline.dto.AirportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    DataSource dataSource;

    @Autowired
    AirportDAO airportDAO;

    @RequestMapping(value = "/", produces =
            MediaType.TEXT_HTML_VALUE,
            method = RequestMethod.GET)
    public String show() {
        return "index";
    }

    @Transactional
    @RequestMapping(value = "/searchAirport", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirportDTO>> searchAirport(@RequestBody AirportDTO airportDTO) {
        List<AirportDTO> suitableAirports = new ArrayList<>();

        List<Airport> allAirports = new ArrayList<>();
        allAirports.addAll((List)airportDAO.findAll());
        allAirports.stream()
                .filter(airport -> airport.getName().toLowerCase().contains(airportDTO.getName().toLowerCase()))
                .forEach(airport -> {
                    AirportDTO suitableAirport = new AirportDTO();
                    suitableAirport.setName(airport.getName());
                    suitableAirports.add(suitableAirport);
                });
        return new ResponseEntity<List<AirportDTO>>(suitableAirports, HttpStatus.OK);
    }
}
