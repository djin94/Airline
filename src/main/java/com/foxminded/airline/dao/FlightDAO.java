package com.foxminded.airline.dao;

import com.foxminded.airline.domain.service.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightDAO extends CrudRepository<Flight, Integer> {

}
