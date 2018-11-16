package com.foxminded.airline.domain.service;

import com.foxminded.airline.dao.repository.AirportRepository;
import com.foxminded.airline.dao.repository.FlightRepository;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Plane;
import com.foxminded.airline.domain.service.impl.FlightServiceImpl;
import com.foxminded.airline.web.dto.FlightDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FlightServiceTest {
    @InjectMocks
    private FlightServiceImpl flightService;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirportRepository airportRepository;

    private Flight flight;
    private LocalDate date;
    private LocalTime time;
    private Plane plane;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private String number;
    private FlightDTO flightDTO;

    @Before
    public void setUp() {

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

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenFindFlightByNumberAndDateAndTime_thenReturnFlight() {
        when(flightRepository.findByNumberAndDateAndTime(number, date, time)).thenReturn(Optional.of(flight));

        Flight expectedFlight = flight;
        Flight actualFlight = flightService.findFlightByNumberAndDateAndTime(number, date.toString(), time.toString());

        assertEquals(expectedFlight, actualFlight);
    }

    @Test
    public void whenFindFlightsByDepartureAirportAndArrivalAirportAndDate_thenReturnFlights() {
        when(airportRepository.findByNameIgnoreCase(flightDTO.getDepartureAirport())).thenReturn(Optional.of(departureAirport));
        when(airportRepository.findByNameIgnoreCase(flightDTO.getArrivalAirport())).thenReturn(Optional.of(arrivalAirport));
        when(flightRepository.findByDepartureAirportAndArrivalAirportAndDate(departureAirport, arrivalAirport, date)).thenReturn(Arrays.asList(flight));

        List<Flight> actualFlights = flightService.findFlightsByDepartureAirportAndArrivalAirportAndDate(flightDTO.getDateString(), flightDTO.getDepartureAirport(), flightDTO.getArrivalAirport());

        assertThat(actualFlights, hasItems(flight));
    }

    @Test
    public void whenFindFlightsForAirportByDate_thenReturnFlightsForAirport() {
        when(airportRepository.findByNameIgnoreCase(flightDTO.getDepartureAirport())).thenReturn(Optional.of(departureAirport));
        when(flightRepository.findByDepartureAirportAndDate(departureAirport, date)).thenReturn(Arrays.asList(flight));

        List<Flight> actualFlights = flightService.findFlightsForAirportByDate(flightDTO.getDateString(), flightDTO.getDepartureAirport());

        assertThat(actualFlights, hasItems(flight));
    }
}