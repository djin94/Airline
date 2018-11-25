package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Plane;
import com.foxminded.airline.web.dto.FlightDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightServiceIntegrationTest {
    @Autowired
    private FlightService flightService;

    private Flight flight;
    private LocalDate date;
    private LocalTime time;
    private String number;
    private FlightDTO flightDTO;

    @Before
    public void setUp() throws Exception {
        Airport arrivalAirport = new Airport();
        arrivalAirport.setName("Stockholm, airport Arlanda");

        Airport departureAirport = new Airport();
        departureAirport.setName("London, airport Heathrow");

        Plane plane = new Plane();
        plane.setName("Boeing 747");

        time = LocalTime.of(8, 5);
        date = LocalDate.of(2018, 10, 1);

        number = "1574";

        flight = new Flight();
        flight.setId((long) 1);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        flight.setDate(date);
        flight.setTime(time);
        flight.setNumber(number);

        flightDTO = new FlightDTO();
        flightDTO.setNumber(number);
        flightDTO.setArrivalAirport(arrivalAirport.getName());
        flightDTO.setDepartureAirport(departureAirport.getName());
        flightDTO.setDateString("2018-10-01");
    }

    @Test
    public void whenFindFlightByNumberAndDateAndTime_thenReturnFlight() {
        Flight expectedFlight = flight;
        Flight actualFlight = flightService.findFlightByNumberAndDateAndTime(number, date.toString(), time.toString());

        assertEquals(expectedFlight, actualFlight);
    }

    @Test
    public void whenFindFlightsByDepartureAirportAndArrivalAirportAndDate_thenReturnFlights() {
        List<Flight> actualFlights = flightService.findFlightsByDepartureAirportAndArrivalAirportAndDate(flightDTO.getDateString(), flightDTO.getDepartureAirport(), flightDTO.getArrivalAirport());

        assertThat(actualFlights, hasItems(flight));
    }

    @Test
    public void whenFindFlightsForAirportByDate_thenReturnFlightsForAirport() {
        List<Flight> actualFlights = flightService.findFlightsForAirportByDate(flightDTO.getDateString(), flightDTO.getDepartureAirport());

        assertThat(actualFlights, hasItems(flight));
    }
}