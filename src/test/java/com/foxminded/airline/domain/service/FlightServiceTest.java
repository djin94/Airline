package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Airline;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Plane;
import com.foxminded.airline.dto.FlightDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan("com.foxminded.airline.domain.service.impl")
public class FlightServiceTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightService flightService;

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
        flightDTO.setDateString("15.10.2018");

        entityManager.persist(airline);
        entityManager.persist(departureAirport);
        entityManager.persist(arrivalAirport);
        entityManager.persist(plane);
        entityManager.persist(flight);
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
    public void whenFindFlightsByFlightDTO_thenReturnFlights(){
        List<Flight> actualFlights = flightService.findFlightsByFlightDTO(flightDTO);

        assertThat(actualFlights, hasItems(flight));
    }

    @Test
    public void whenFindFlightsForAirportByDate_thenFindFlightsForAirportByDate(){
        List<Flight> actualFlights = flightService.findFlightsForAirportByDate(flightDTO);

        assertThat(actualFlights,hasItems(flight));
    }
}