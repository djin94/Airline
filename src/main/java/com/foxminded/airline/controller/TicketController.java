package com.foxminded.airline.controller;

import com.foxminded.airline.dao.FlightDAO;
import com.foxminded.airline.dao.FlightPriceDAO;
import com.foxminded.airline.dao.PassengerDAO;
import com.foxminded.airline.dao.TicketDAO;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.FlightPrice;
import com.foxminded.airline.domain.entity.Passenger;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.dto.FlightDTO;
import com.foxminded.airline.dto.FlightPriceDTO;
import com.foxminded.airline.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "/buyticket",
            params = {"number", "dateString", "timeString"})
    public String showTicket(@RequestParam("number") String number,
                             @RequestParam("dateString") String dateString,
                             @RequestParam("timeString") String timeString) {
        flight = flightDAO.findByNumberAndDateAndTime(number, LocalDate.parse(dateString), LocalTime.parse(timeString));
        return "buyTicket";
    }

    @RequestMapping("/buyticket")
    public String showAllTickets() {
        return "hello";
    }

    @RequestMapping(value = "/buyticket/flightprices",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public ResponseEntity<List<FlightPriceDTO>> getFlightPrice() throws IOException {
        List<FlightPrice> flightPrices = flight.getFlightPrices();
        List<FlightPriceDTO> flightPriceDTOS = new ArrayList<>();
        flightPrices.forEach(flightPrice -> {
            FlightPriceDTO flightPriceDTO = new FlightPriceDTO();
            flightPriceDTO.setLevel(flightPrice.getLevel());
            flightPriceDTO.setPrice(String.valueOf((float) flightPrice.getPrice() / 100));
            flightPriceDTOS.add(flightPriceDTO);
        });
        return new ResponseEntity<List<FlightPriceDTO>>(flightPriceDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/buyticket",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public void createTicket(@RequestBody TicketDTO ticketDTO) {
        Passenger passenger = passengerDAO.findByPassportNumber(ticketDTO.getPassportNumber()).orElse(createPassengerFromTicketDTO(ticketDTO));
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setFlightPrice(flightPriceDAO.findByFlightAndLevel(flight, ticketDTO.getFlightPrice().split(" - ")[0]));
        ticket.setPassenger(passenger);
        ticket.setPlace(ticketDTO.getPlace());
        passenger.add(ticket);
        passengerDAO.save(passenger);
        flight.add(ticket);
        flightDAO.save(flight);
    }

    @RequestMapping(value = "/buyticket/success")
    public String buyTicketSuccess(){
        return "ticketCreate";
    }

    private Passenger createPassengerFromTicketDTO(TicketDTO ticketDTO){
        Passenger passenger = new Passenger();
        passenger.setFirstName(ticketDTO.getFirstName());
        passenger.setLastName(ticketDTO.getLastName());
        passenger.setPatronym(ticketDTO.getPatronym());
        passenger.setPassportNumber(ticketDTO.getPassportNumber());
        passengerDAO.save(passenger);
        return passenger;
    }
}
