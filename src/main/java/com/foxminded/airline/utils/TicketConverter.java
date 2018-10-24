package com.foxminded.airline.utils;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.dto.TicketDTO;
import com.foxminded.airline.web.dao.FlightPriceRepository;
import com.foxminded.airline.web.dao.SitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {
    @Autowired
    FlightPriceRepository flightPriceRepository;

    @Autowired
    UserService userService;

    @Autowired
    SitRepository sitRepository;

    public Ticket createTicket(TicketDTO ticketDTO, Flight flight){
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setUser(userService.findOrCreateUserFromUserDTO(ticketDTO.getUserDTO()));
        ticket.setSit(sitRepository.findByPlaneAndPlace(flight.getPlane(),ticketDTO.getSit()));
        return ticket;
    }
}
