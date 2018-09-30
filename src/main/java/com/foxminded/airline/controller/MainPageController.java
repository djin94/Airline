package com.foxminded.airline.controller;

import com.foxminded.airline.dao.AirportDAO;
import com.foxminded.airline.domain.Airport;
import com.foxminded.airline.dto.AirportDTO;
import com.foxminded.airline.utils.HibernateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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



    @RequestMapping(value = "/searchAirport", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AirportDTO>> searchAirport(@RequestBody AirportDTO airportDTO) {
        List<AirportDTO> suitableAirports = new ArrayList<>();
        if(airportDTO.getName()!=null){
        AirportDAO airportDAO = new AirportDAO();
        if (airportDAO==null){
            System.out.println("AirportDAO is null");
        }
        List<Airport> allAirports = airportDAO.getAll();
                allAirports.stream()
                .filter(airport -> airport.getName().toLowerCase().contains(airportDTO.getName().toLowerCase()))
                        .forEach(airport -> {
                            AirportDTO suitableAirport = new AirportDTO();
                            suitableAirport.setName(airport.getName());
                            suitableAirports.add(suitableAirport);
                        });}
        return new ResponseEntity<List<AirportDTO>>(suitableAirports, HttpStatus.OK);
    }
}
