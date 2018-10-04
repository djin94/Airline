package com.foxminded.airline.controller;

import com.foxminded.airline.dao.AirportDAO;
import com.foxminded.airline.domain.entity.Airport;
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
    @RequestMapping(value = "/searchAirport", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirportDTO>> searchAirport(@RequestBody AirportDTO airportDTO) {
        List<Airport> suitableAirports = airportDAO.findByNameLikeIgnoreCase("%" + airportDTO.getName() + "%");
        List<AirportDTO> suitableAirportsDTO = new ArrayList<>();
        suitableAirports.forEach(airport -> {
            AirportDTO suitableAirportDTO = new AirportDTO();
            suitableAirportDTO.setName(airport.getName());
            suitableAirportsDTO.add(suitableAirportDTO);
        });
        return new ResponseEntity<List<AirportDTO>>(suitableAirportsDTO, HttpStatus.OK);
    }
}
