package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.Passenger;
import org.springframework.data.repository.CrudRepository;

public interface PassengerDAO extends CrudRepository<Passenger, Integer> {
}
