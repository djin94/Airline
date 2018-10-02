package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.Airport;;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AirportDAO extends CrudRepository<Airport, Integer> {
    List<Airport> findByNameLikeIgnoreCase(String name);
    Airport findByNameIgnoreCase(String name);
}
