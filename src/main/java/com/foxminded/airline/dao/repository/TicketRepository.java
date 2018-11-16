package com.foxminded.airline.dao.repository;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    List<Ticket> findByFlight(Flight flight);

    List<Ticket> findByUser(User user);
}
