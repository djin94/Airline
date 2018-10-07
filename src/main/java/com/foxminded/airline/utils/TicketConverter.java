package com.foxminded.airline.utils;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.service.PassengerService;
import com.foxminded.airline.dto.TicketDTO;
import com.foxminded.airline.web.dao.FlightPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("ticketConverter")
public class TicketConverter {
    @Autowired
    DataSource dataSource;

    @Autowired
    FlightPriceRepository flightPriceRepository;

    @Autowired
    PassengerService passengerService;

    public Ticket createTicket(TicketDTO ticketDTO, Flight flight){
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setFlightPrice(flightPriceRepository.findByFlightAndLevel(flight, ticketDTO.getFlightPrice().split(" - ")[0]).get());
        ticket.setPassenger(passengerService.findOrCreatePassengerFromPassengerDTO(ticketDTO.getPassengerDTO()));
        ticket.setPlace(ticketDTO.getPlace());
        return ticket;
    }
}
