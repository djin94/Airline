package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Airline;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Plane;
import com.foxminded.airline.web.dao.AirlineRepository;
import com.foxminded.airline.web.dao.AirportRepository;
import com.foxminded.airline.web.dao.FlightRepository;
import com.foxminded.airline.web.dao.PlaneRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class FlightServiceTest {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    AirlineRepository airlineRepository;

    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    FlightService flightService;

    private Flight flight;
    private LocalDate date;
    private LocalTime time;
    private Plane plane;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private String number;

    @Before
    public void setUp() {
        airline=new Airline();
        airline.setName("Qatar Airways");

        arrivalAirport = new Airport();
        arrivalAirport.setName("London");

        departureAirport = new Airport();
        departureAirport.setName("Berlin");

        plane = new Plane();
        plane.setName("Boeing 737");

        time = LocalTime.of(7, 45);
        date = LocalDate.of(2018, 10, 15);

        number="7845";

        flight = new Flight();
        flight.setAirline(airline);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        flight.setDate(date);
        flight.setTime(time);
        flight.setNumber(number);

        airlineRepository.save(airline);
        airportRepository.save(departureAirport);
        airportRepository.save(arrivalAirport);
        planeRepository.save(plane);
        flightRepository.save(flight);
    }

    @After
    public void tearDown() {
        airlineRepository.delete(airline);
        airportRepository.delete(departureAirport);
        airportRepository.delete(arrivalAirport);
        planeRepository.delete(plane);
        flightRepository.delete(flight);
    }

    @Test
    public void whenFindFlightByNumberAndDateAndTime_thenReturnFlight(){
        Flight expectedFlight = flight;
        Flight actualFlight = flightService.findFlightByNumberAndDateAndTime(number, date, time);

        assertEquals(expectedFlight,actualFlight);
    }
}