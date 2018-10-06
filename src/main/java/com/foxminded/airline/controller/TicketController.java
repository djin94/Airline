package com.foxminded.airline.controller;

import com.foxminded.airline.dao.TicketRepository;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.dto.FlightPriceDTO;
import com.foxminded.airline.dto.TicketDTO;
import com.foxminded.airline.utils.FlightPriceConverter;
import com.foxminded.airline.utils.TicketConverter;
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
    TicketRepository ticketRepository;

    @GetMapping(value = "/buyticket",
            params = {"number", "dateString", "timeString"})
    public String showTicket(@RequestParam("number") String number,
                             @RequestParam("dateString") String dateString,
                             @RequestParam("timeString") String timeString) {
        flight = flightService.findFlightByNumberAndDateAndTime(number, LocalDate.parse(dateString), LocalTime.parse(timeString));
        return "buyTicket";
    }

    @GetMapping(value = "/buyticket/flightprices",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FlightPriceDTO>> getFlightPrices() throws IOException {
        return new ResponseEntity<>(new FlightPriceConverter().createDTOsForFlightPrices(flight.getFlightPrices()), HttpStatus.OK);
    }

    @PostMapping(value = "/buyticket",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        ticketRepository.save(new TicketConverter().createTicket(ticketDTO, flight));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
