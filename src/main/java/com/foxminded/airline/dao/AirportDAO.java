package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Airport;;
import org.springframework.data.repository.CrudRepository;

public interface AirportDAO extends CrudRepository<Airport, Integer> {

}
