package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Airline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends CrudRepository<Airline, Integer> {

}
