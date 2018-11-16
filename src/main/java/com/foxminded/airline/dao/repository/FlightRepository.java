package com.foxminded.airline.dao.repository;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Integer> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDate(Airport departureAirport, Airport arrivalAirport, LocalDate date);

    Optional<Flight> findByNumberAndDateAndTime(String number, LocalDate date, LocalTime time);

    List<Flight> findByDepartureAirportAndDate(Airport departureAirport, LocalDate date);

    List<Flight> findByArrivalAirportAndDate(Airport arrivalAirport, LocalDate date);
}
