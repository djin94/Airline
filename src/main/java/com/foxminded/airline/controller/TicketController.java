package com.foxminded.airline.controller;

import com.foxminded.airline.dto.FlightDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TicketController {
    FlightDTO flightDTO;
    @RequestMapping(value = "/buyticket",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public String showTicket(@RequestBody FlightDTO flightDTO) {
        this.flightDTO = flightDTO;
        return "hello";
    }
}
