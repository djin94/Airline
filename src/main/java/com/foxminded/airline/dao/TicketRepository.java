package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}
