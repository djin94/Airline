package com.foxminded.airline.dao.repository;

import com.foxminded.airline.domain.entity.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Integer> {
    List<Airport> findByNameLikeIgnoreCase(String name);

    Optional<Airport> findByNameIgnoreCase(String name);
}
