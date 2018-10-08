package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.LevelTicket;
import org.springframework.data.repository.CrudRepository;

public interface LevelTicketRepository extends CrudRepository<LevelTicket, Integer> {
    LevelTicket findByLevel(String level);
}
