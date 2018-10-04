package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AirlineDAO extends CrudRepository<Airline, Integer> {

}
