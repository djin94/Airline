package com.foxminded.airline.dao;

import com.foxminded.airline.domain.service.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketDAO extends CrudRepository<Ticket, Integer> {

}
