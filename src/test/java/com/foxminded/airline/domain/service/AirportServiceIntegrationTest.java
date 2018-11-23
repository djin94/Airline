package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.service.AirportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AirportServiceIntegrationTest {

    @Autowired
    private AirportService airportService;

    private List<Airport> airports;
    private String namePart;

    @Before
    public void setUp() throws Exception {
        Airport airport = new Airport();
        airport.setId((long) 1);
        airport.setName("London, airport Heathrow");

        airports = new ArrayList<>();
        airports.add(airport);

        namePart = "London";
    }

    @Test
    public void whenFindAirportsByNamePart_thenReturnAirportsIfExist() {
        List<Airport> expectedAirports = airports;
        List<Airport> actualAirports = airportService.findAirportsByNamePart(namePart);

        assertEquals(expectedAirports, actualAirports);
    }
}