package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Airline;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Plane;
import com.foxminded.airline.dto.FlightDTO;
import com.foxminded.airline.web.dao.FlightRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.foxminded.airline.domain.service.impl")
@SpringBootTest
public class FlightServiceTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    private Flight flight;
    private LocalDate date;
    private LocalTime time;
    private Plane plane;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Airline airline;
    private String number;
    private FlightDTO flightDTO;

    @Before
    public void setUp() {
        airline = new Airline();
        airline.setName("Qatar Airways");

        arrivalAirport = new Airport();
        arrivalAirport.setName("London");

        departureAirport = new Airport();
        departureAirport.setName("Berlin");

        plane = new Plane();
        plane.setName("Boeing 737");

        time = LocalTime.of(7, 45);
        date = LocalDate.of(2018, 10, 15);

        number = "7845";

        flight = new Flight();
        flight.setAirline(airline);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        flight.setDate(date);
        flight.setTime(time);
        flight.setNumber(number);

        flightDTO = new FlightDTO();
        flightDTO.setNumber(number);
        flightDTO.setArrivalAirport(arrivalAirport.getName());
        flightDTO.setDepartureAirport(departureAirport.getName());
        flightDTO.setDateString("2018-10-15");

        entityManager.persistAndFlush(airline);
        entityManager.persistAndFlush(departureAirport);
        entityManager.persistAndFlush(arrivalAirport);
        entityManager.persistAndFlush(plane);
        entityManager.persistAndFlush(flight);
    }

    @After
    public void tearDown() {
        entityManager.remove(flight);
        entityManager.remove(airline);
        entityManager.remove(departureAirport);
        entityManager.remove(arrivalAirport);
        entityManager.remove(plane);
    }

    @Test
    public void whenFindFlightByNumberAndDateAndTime_thenReturnFlight() {
        Flight expectedFlight = flight;
        Flight actualFlight = flightService.findFlightByNumberAndDateAndTime(number, date, time);

        assertEquals(expectedFlight, actualFlight);
    }

    @Test
    @Ignore
    public void whenFindFlightsByFlightDTO_thenReturnFlights() {
        List<Flight> actualFlights = flightService.findFlightsByFlightDTO(flightDTO);

        assertThat(actualFlights, hasItems(flight));
    }

    @Test
    @Ignore
    public void whenFindFlightsForAirportByDate_thenFindFlightsForAirportByDate() {
        List<Flight> actualFlights = flightService.findFlightsForAirportByDate(flightDTO);

        assertThat(actualFlights, hasItems(flight));
    }
}