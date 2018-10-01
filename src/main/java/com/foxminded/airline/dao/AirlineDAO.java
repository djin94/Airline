package com.foxminded.airline.dao;

import com.foxminded.airline.domain.Airline;
import com.foxminded.airline.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AirlineDAO extends CrudRepository<Airline, Integer> {

}
