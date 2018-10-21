package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.*;
import com.foxminded.airline.domain.service.SitService;
import com.foxminded.airline.utils.TicketConverter;
import com.foxminded.airline.web.dao.TicketRepository;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.dto.FlightPriceDTO;
import com.foxminded.airline.dto.TicketDTO;
import com.foxminded.airline.utils.FlightPriceConverter;
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

    @Autowired
    TicketConverter ticketConverter;

    @Autowired
    FlightPriceConverter flightPriceConverter;

    @Autowired
    SitService sitService;

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
        return new ResponseEntity<>(flightPriceConverter.createDTOsForFlightPrices(flight.getFlightPrices()), HttpStatus.OK);
    }

    @PostMapping(value = "/buyticket/sits",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sit>> getSits(@RequestBody Sit sit) throws IOException {
        String levelTicket;
        if (sit.getLevelTicket() == null)
            levelTicket = LevelTicket.ECONOM.getLevelTicket();
        else
            levelTicket = sit.getLevelTicket().split(" - ")[0];
        return new ResponseEntity<>(sitService.findAvailableSitsForFlightAndLevelTicket(flight, levelTicket), HttpStatus.OK);
    }

    @PostMapping(value = "/buyticket",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        ticketRepository.save(ticketConverter.createTicket(ticketDTO, flight));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
