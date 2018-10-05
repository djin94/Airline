package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.dto.TicketDTO;

public interface TicketService {
    Ticket createTicket(TicketDTO ticketDTO, Flight flight);

    void save(Ticket ticket);
}
