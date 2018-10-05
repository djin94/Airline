package com.foxminded.airline.controller;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.service.FlightPriceService;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.domain.service.TicketService;
import com.foxminded.airline.dto.FlightPriceDTO;
import com.foxminded.airline.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class TicketController {
    Flight flight;

    @Autowired
    FlightService flightService;

    @Autowired
    FlightPriceService flightPriceService;

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/buyticket",
            params = {"number", "dateString", "timeString"})
    public String showTicket(@RequestParam("number") String number,
                             @RequestParam("dateString") String dateString,
                             @RequestParam("timeString") String timeString) {
        flight = flightService.findFlightByNumberAndDateAndTime(number, LocalDate.parse(dateString), LocalTime.parse(timeString));
        return "buyTicket";
    }

    @RequestMapping(value = "/buyticket/flightprices",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public ResponseEntity<List<FlightPriceDTO>> getFlightPrice() throws IOException {
        return new ResponseEntity<List<FlightPriceDTO>>(flightPriceService.createDTOsForFlightPrices(flight.getFlightPrices()), HttpStatus.OK);
    }

    @RequestMapping(value = "/buyticket",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.save(ticketService.createTicket(ticketDTO, flight));
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
