package com.foxminded.airline.dao;

import com.foxminded.airline.domain.FlightPrice;
import org.springframework.data.repository.CrudRepository;


public interface FlightPriceDAO extends CrudRepository<FlightPrice, Integer> {
}
