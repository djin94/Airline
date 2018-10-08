package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.FlightPrice;
import com.foxminded.airline.domain.entity.LevelTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface FlightPriceRepository extends CrudRepository<FlightPrice, Integer> {
    Optional<FlightPrice> findByFlightAndLevelTicket(Flight flight, LevelTicket levelTicket);
}
