package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FlightDAO extends CrudRepository<Flight, Integer> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDate(Airport departureAirport, Airport arrivalAirport, LocalDate date);

    Flight findByNumberAndDateAndTime(String number, LocalDate date, LocalTime time);
}
