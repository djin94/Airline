package com.foxminded.airline.web.dao;

import com.foxminded.airline.domain.entity.Passenger;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PassengerRepository extends CrudRepository<Passenger, Integer> {
    Optional<Passenger> findByPassportNumber(String passportNumber);
}
