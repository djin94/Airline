package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.service.impl.AirportServiceImpl;
import com.foxminded.airline.web.repository.AirportRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AirportServiceTest {

    @InjectMocks
    private AirportServiceImpl airportService;

    @Mock
    private AirportRepository airportRepository;

    private Airport airportHeathrow;
    private String airportNamePart;

    @Before
    public void setUp() throws Exception {
        airportHeathrow = new Airport();
        airportHeathrow.setName("London, airportHeathrow Heathrow");

        airportNamePart = "London";

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenFindAirportsByNamePart_thenReturnAirports() {
        when(airportRepository.findByNameLikeIgnoreCase("%" + airportNamePart + "%")).thenReturn(Arrays.asList(airportHeathrow));

        List<Airport> actualAirports = airportService.findAirportsByNamePart(airportNamePart);

        assertThat(actualAirports, hasItems(airportHeathrow));
    }
}