package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightDAO extends CrudRepository<Flight, Integer> {

}
