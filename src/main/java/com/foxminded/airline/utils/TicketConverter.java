package com.foxminded.airline.utils;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.service.PassengerService;
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
    PassengerService passengerService;

    @Autowired
    SitRepository sitRepository;

    public Ticket createTicket(TicketDTO ticketDTO, Flight flight){
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setPassenger(passengerService.findOrCreatePassengerFromPassengerDTO(ticketDTO.getPassengerDTO()));
        ticket.setSit(sitRepository.findByPlaneAndPlace(flight.getPlane(),ticketDTO.getSit()));
        return ticket;
    }
}
