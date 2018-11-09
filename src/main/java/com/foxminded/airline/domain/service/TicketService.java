package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket save(Ticket ticket);

    List<Ticket> findTicketsByFlight(Flight flight);
}
