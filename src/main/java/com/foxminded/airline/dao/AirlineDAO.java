package com.foxminded.airline.dao;

import com.foxminded.airline.domain.service.Airline;
import org.springframework.data.repository.CrudRepository;

public interface AirlineDAO extends CrudRepository<Airline, Integer> {

}
