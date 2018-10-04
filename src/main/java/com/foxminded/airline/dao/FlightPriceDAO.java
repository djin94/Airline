package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.FlightPrice;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface FlightPriceDAO extends CrudRepository<FlightPrice, Integer> {
    FlightPrice findByFlightAndLevel(Flight flight, String level);
}
