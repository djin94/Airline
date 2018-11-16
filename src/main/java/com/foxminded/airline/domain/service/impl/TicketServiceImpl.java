package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.domain.entity.*;
import com.foxminded.airline.domain.service.TicketService;
import com.foxminded.airline.dao.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> findTicketsByFlight(Flight flight) {
        return ticketRepository.findByFlight(flight);
    }

    @Override
    public List<Ticket> findTicketsByUser(User user) {
        return ticketRepository.findByUser(user);
    }
}
