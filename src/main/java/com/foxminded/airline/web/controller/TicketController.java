package com.foxminded.airline.web.controller;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Sit;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.domain.service.SitService;
import com.foxminded.airline.domain.service.TicketService;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.web.dto.FlightPriceDTO;
import com.foxminded.airline.web.dto.TicketDTO;
import com.foxminded.airline.domain.service.utils.FlightPriceConverter;
import com.foxminded.airline.domain.service.utils.TicketConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class TicketController {
    private Flight flight;

    @Autowired
    private FlightService flightService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private FlightPriceConverter flightPriceConverter;

    @Autowired
    private SitService sitService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/buyticket",
            params = {"number", "dateString", "timeString"})
    public String showTicket(@RequestParam("number") String number,
                             @RequestParam("dateString") String dateString,
                             @RequestParam("timeString") String timeString) {
        flight = flightService.findFlightByNumberAndDateAndTime(number, dateString, timeString);
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
        return new ResponseEntity<>(sitService.findAvailableSitsForFlightAndLevelTicket(flight, sitService.getLevelTicketFromSitOrDefault(sit)), HttpStatus.OK);
    }

    @PostMapping(value = "/buyticket",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.save(ticketConverter.createTicketFromDTO(ticketDTO, flight));
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping(value = "/user/buyticket",
            params = {"number", "dateString", "timeString"})
    public String showTicketForUser(@RequestParam("number") String number,
                                    @RequestParam("dateString") String dateString,
                                    @RequestParam("timeString") String timeString) {
        flight = flightService.findFlightByNumberAndDateAndTime(number, dateString, timeString);
        return "user/buyTicket";
    }

    @PostMapping(value = "/user/buyticket",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTicketForUser(@RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketConverter.createTicketFromDTOForUser(ticketDTO, flight, userService.getCurrentUser());
        ticketService.save(ticket);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping(value = "/admin/listtickets",
            params = {"number", "dateString", "timeString"})
    public String showPurchasedTickets(@RequestParam("number") String number,
                                       @RequestParam("dateString") String dateString,
                                       @RequestParam("timeString") String timeString) {
        flight = flightService.findFlightByNumberAndDateAndTime(number, dateString, timeString);
        return "admin/listTickets";
    }

    @PostMapping(value = "/admin/listtickets")
    public ResponseEntity<List<TicketDTO>> getListTickets() {
        return new ResponseEntity<>(ticketConverter.createTicketDTOsFromTickets(ticketService.findTicketsByFlight(flight)), HttpStatus.OK);
    }
}