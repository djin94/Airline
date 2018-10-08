package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Passenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Integer> {
    Optional<Passenger> findByPassportNumber(String passportNumber);
}
