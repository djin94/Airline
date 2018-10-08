package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.*;
import com.foxminded.airline.domain.service.PassengerService;
import com.foxminded.airline.domain.service.SitService;
import com.foxminded.airline.utils.TicketConverter;
import com.foxminded.airline.web.dao.FlightPriceRepository;
import com.foxminded.airline.web.dao.LevelTicketRepository;
import com.foxminded.airline.web.dao.SitRepository;
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

import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class TicketController {
    Flight flight;

    @Autowired
    DataSource dataSource;

    @Autowired
    FlightService flightService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketConverter ticketConverter;

    @Autowired
    FlightPriceConverter flightPriceConverter;

    @Autowired
    LevelTicketRepository levelTicketRepository;

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
    public ResponseEntity<List<Sit>> getSits(@RequestBody LevelTicket levelTicket) throws IOException {
        if (levelTicket.getLevel() == null)
            levelTicket = levelTicketRepository.findById(1).get();
        else
            levelTicket = levelTicketRepository.findByLevel(levelTicket.getLevel().split(" - ")[0]);
        return new ResponseEntity<>(sitService.findAvailableSitsForFlightAndLevelTicket(flight, levelTicket), HttpStatus.OK);
    }

    @PostMapping(value = "/buyticket",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        ticketRepository.save(ticketConverter.createTicket(ticketDTO, flight));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
