package com.foxminded.airline.domain.service;

import com.foxminded.airline.dao.FlightPriceDAO;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.dto.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {
    @Autowired
    FlightPriceDAO flightPriceDAO;

    @Autowired
    PassengerService passengerService;

    @Override
    public Ticket createTicket(TicketDTO ticketDTO, Flight flight) {
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setFlightPrice(flightPriceDAO.findByFlightAndLevel(flight, ticketDTO.getFlightPrice().split(" - ")[0]));
        ticket.setPassenger(passengerService.findOrCreatePassengerFromPassengerDTO(ticketDTO.getPassengerDTO()));
        ticket.setPlace(ticketDTO.getPlace());
        return ticket;
    }
}
