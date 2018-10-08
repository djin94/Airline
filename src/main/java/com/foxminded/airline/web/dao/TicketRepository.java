package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findByFlight(Flight flight);
}
