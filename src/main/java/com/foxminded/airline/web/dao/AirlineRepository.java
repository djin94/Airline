package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Airline;
import org.springframework.data.repository.CrudRepository;

public interface AirlineRepository extends CrudRepository<Airline, Integer> {

}
