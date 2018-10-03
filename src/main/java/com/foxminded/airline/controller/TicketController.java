package com.foxminded.airline.controller;

import com.foxminded.airline.dto.FlightDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {
    FlightDTO flightDTO;
    @RequestMapping(value = "/buyticket",
            produces = MediaType.TEXT_HTML_VALUE,
            params = {"number", "dateString", "timeString"},
            method = RequestMethod.GET)
    public String showTicket(@RequestParam("number") String number,
                             @RequestParam("dateString") String dateString,
                             @RequestParam("timeString") String timeString) {
        flightDTO = new FlightDTO();
        flightDTO.setNumber(number);
        flightDTO.setDateString(dateString);
        flightDTO.setTimeString(timeString);
        return "hello";
    }
}
