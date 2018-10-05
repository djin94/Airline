package com.foxminded.airline.controller;

import com.foxminded.airline.dao.FlightDAO;
import com.foxminded.airline.dao.FlightPriceDAO;
import com.foxminded.airline.dao.PassengerDAO;
import com.foxminded.airline.dao.TicketDAO;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Passenger;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.service.FlightPriceService;
import com.foxminded.airline.domain.service.PassengerService;
import com.foxminded.airline.domain.service.TicketService;
import com.foxminded.airline.dto.FlightDTO;
import com.foxminded.airline.dto.FlightPriceDTO;
import com.foxminded.airline.dto.PassengerDTO;
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
    FlightDTO flightDTO;
    Flight flight;

    @Autowired
    FlightDAO flightDAO;

    @Autowired
    FlightPriceDAO flightPriceDAO;

    @Autowired
    PassengerDAO passengerDAO;

    @Autowired
    TicketDAO ticketDAO;

    @Autowired
    FlightPriceService flightPriceService;

    @Autowired
    PassengerService passengerService;

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "/buyticket",
            params = {"number", "dateString", "timeString"})
    public String showTicket(@RequestParam("number") String number,
                             @RequestParam("dateString") String dateString,
                             @RequestParam("timeString") String timeString) {
        flight = flightDAO.findByNumberAndDateAndTime(number, LocalDate.parse(dateString), LocalTime.parse(timeString));
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
        ticketDAO.save(ticketService.createTicket(ticketDTO, flight));
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
