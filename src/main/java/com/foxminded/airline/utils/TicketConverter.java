package com.foxminded.airline.utils;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.dto.TicketDTO;
import com.foxminded.airline.web.repository.SitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketConverter {

    @Autowired
    private SitRepository sitRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private FlightConverter flightConverter;

    public Ticket createTicketFromDTO(TicketDTO ticketDTO, Flight flight) {
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setUser(userConverter.createUserFromUserDTO(ticketDTO.getUserDTO()));
        ticket.setSit(sitRepository.findByPlaneAndPlace(flight.getPlane(), ticketDTO.getSit()));
        return ticket;
    }

    public Ticket createTicketFromDTOForUser(TicketDTO ticketDTO, Flight flight, User user) {
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setUser(user);
        ticket.setSit(sitRepository.findByPlaneAndPlace(flight.getPlane(), ticketDTO.getSit()));
        return ticket;
    }

    public List<TicketDTO> createTicketDTOsFromTickets(List<Ticket> tickets) {
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        tickets.forEach(ticket -> {
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setSit(ticket.getSit().getPlace());
            ticketDTO.setUserDTO(userConverter.createUserDTOFromUser(ticket.getUser()));
            ticketDTO.setFlightDTO(flightConverter.createFlightDTOFromFlight(ticket.getFlight()));
            ticketDTOs.add(ticketDTO);
        });
        return ticketDTOs;
    }
}
