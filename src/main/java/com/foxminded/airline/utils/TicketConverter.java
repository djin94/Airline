package com.foxminded.airline.utils;

import com.foxminded.airline.dao.FlightPriceRepository;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.service.PassengerService;
import com.foxminded.airline.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class TicketConverter {
    @Autowired
    DataSource dataSource;

    @Autowired
    FlightPriceRepository flightPriceRepository;

    @Autowired
    PassengerService passengerService;
    public Ticket createTicket(TicketDTO ticketDTO, Flight flight) {
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setFlightPrice(flightPriceRepository.findByFlightAndLevel(flight, ticketDTO.getFlightPrice().split(" - ")[0]));
        ticket.setPassenger(passengerService.findOrCreatePassengerFromPassengerDTO(ticketDTO.getPassengerDTO()));
        ticket.setPlace(ticketDTO.getPlace());
        return ticket;
    }
}
