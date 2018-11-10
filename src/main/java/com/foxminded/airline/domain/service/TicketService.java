package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Sit;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.entity.User;

import java.util.List;

public interface TicketService {
    Ticket save(Ticket ticket);

    List<Ticket> findTicketsByFlight(Flight flight);

    String getLevelTicketFromSitOrDefault(Sit sit);

    List<Ticket> findTicketsByUser(User user);
}
