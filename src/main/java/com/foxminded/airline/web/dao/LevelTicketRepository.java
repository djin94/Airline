package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.LevelTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelTicketRepository extends CrudRepository<LevelTicket, Integer> {
    LevelTicket findByLevel(String level);
}
