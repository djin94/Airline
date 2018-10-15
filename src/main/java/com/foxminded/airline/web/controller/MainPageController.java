package com.foxminded.airline.web.controller;

import com.foxminded.airline.web.dao.AirportRepository;
import com.foxminded.airline.domain.entity.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    AirportRepository airportRepository;

    @RequestMapping(name = "/",produces = MediaType.TEXT_HTML_VALUE,headers={"Accept=text/plain"})
    public String showMainPage() {
        return "index";
    }

    @RequestMapping(name="/searchAirport", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<Airport>> searchAirport(@RequestBody Airport airport) {
        return new ResponseEntity<>(airportRepository.findByNameLikeIgnoreCase("%" + airport.getName() + "%"), HttpStatus.OK);
    }
}
