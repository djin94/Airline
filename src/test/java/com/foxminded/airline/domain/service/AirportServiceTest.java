package com.foxminded.airline.domain.service;

import com.foxminded.airline.dao.repository.AirportRepository;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.service.impl.AirportServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AirportServiceTest {

    @InjectMocks
    private AirportServiceImpl airportService;

    @Mock
    private AirportRepository airportRepository;

    private Airport airportHeathrow;
    private String airportNamePart;
    private String notExistAirportNamePart;

    @Before
    public void setUp() throws Exception {
        airportHeathrow = new Airport();
        airportHeathrow.setName("London, airportHeathrow Heathrow");

        airportNamePart = "London";

        notExistAirportNamePart = "Berlin";

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenFindAirportsByNamePart_thenReturnAirportsIfExist() {
        when(airportRepository.findByNameLikeIgnoreCase("%" + airportNamePart + "%")).thenReturn(asList(airportHeathrow));

        List<Airport> actualAirports = airportService.findAirportsByNamePart(airportNamePart);

        assertThat(actualAirports, hasItems(airportHeathrow));
    }

    @Test
    public void whenFindAirportsByNamePart_thenReturnEmptyListAirportsIfNotExist() {
        when(airportRepository.findByNameLikeIgnoreCase("%" + notExistAirportNamePart + "%")).thenReturn(Collections.emptyList());

        List<Airport> actualAirports = airportService.findAirportsByNamePart(notExistAirportNamePart);

        assertEquals(actualAirports.size(), 0);
    }
}