package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.Passenger;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PassengerDAO extends CrudRepository<Passenger, Integer> {
    Optional<Passenger> findByPassportNumber(String passportNumber);
}
