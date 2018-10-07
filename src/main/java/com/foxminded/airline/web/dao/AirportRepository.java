package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Airport;;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends CrudRepository<Airport, Integer> {
    List<Airport> findByNameLikeIgnoreCase(String name);

    Optional<Airport> findByNameIgnoreCase(String name);
}
