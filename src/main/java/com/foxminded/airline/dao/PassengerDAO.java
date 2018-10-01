package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Passenger;
import org.springframework.data.repository.CrudRepository;

public interface PassengerDAO extends CrudRepository<Passenger, Integer> {
}
