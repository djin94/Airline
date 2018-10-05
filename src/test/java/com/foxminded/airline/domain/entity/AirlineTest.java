package com.foxminded.airline.domain.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AirlineTest {
    private Airline airline;
    private Flight flight;
    private List<Flight> storedFlights;
    private List<Flight> emptyStoredFlights;

    @Before
    public void setUp() {
        airline = new Airline();
        flight = new Flight();
        flight.setId(1);
        storedFlights = new ArrayList<>();
        storedFlights.add(flight);
        airline.add(flight);
        emptyStoredFlights = new ArrayList<>();
    }

    @Test
    public void shouldAddFlight_WhenAddFlight() {
        List<Flight> expectedStoredFlights = storedFlights;
        List<Flight> actualStoredFlights = airline.getFlights();

        assertEquals(expectedStoredFlights, actualStoredFlights);
    }

    @Test
    public void shouldRemoveFlight_WhenRemoveFlight() {
        airline.remove(flight);

        List<Flight> expectedStoredFlights = emptyStoredFlights;
        List<Flight> actualStoredFlights = airline.getFlights();

        assertEquals(expectedStoredFlights, actualStoredFlights);
    }
}