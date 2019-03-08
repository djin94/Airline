package com.foxminded.airline.domain.service.impl;

import com.foxminded.airline.dao.repository.TicketRepository;
import com.foxminded.airline.dao.repository.UserRepository;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Ticket save(Ticket ticket) {
        Optional<User> user = userRepository.findByPassportNumber(ticket.getUser().getPassportNumber());
        if (user.isPresent()){
            ticket.setUser(user.get());
        }
        else {
            userRepository.save(ticket.getUser());
        }
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
