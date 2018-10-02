package com.foxminded.airline.dao;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightDAO extends CrudRepository<Flight, Integer> {
//    @Query("select f from Flight f where f.departureAirport=:departureAirport and f.arrivalAirport=:arrivalAirport and f.date. = :date")
    List<Flight> findByDepartureAirportAndArrivalAirportAndDate(Airport departureAirport, Airport arrivalAirport, LocalDate date);
}
