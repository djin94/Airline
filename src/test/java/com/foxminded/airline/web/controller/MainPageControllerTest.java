package com.foxminded.airline.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.airline.domain.entity.Airport;
import com.foxminded.airline.domain.service.AirportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MainPageControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private MainPageController mainPageController;

    @Mock
    private AirportService airportService;

    private Airport airport;
    private List<Airport> airports;
    private List<Airport> emptyListAirports;
    private String airportNamePart;
    private String notExistAirportNamePart;
    private Airport airportWithNamePart;
    private Airport notExistAirportWithNamePart;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        airport = new Airport();
        airport.setName("London, airportHeathrow Heathrow");

        airportNamePart = "London";
        notExistAirportNamePart = "Berlin";

        airportWithNamePart = new Airport();
        airportWithNamePart.setName(airportNamePart);

        notExistAirportWithNamePart = new Airport();
        notExistAirportWithNamePart.setName(notExistAirportNamePart);

        airports = new ArrayList<>();
        airports.add(airport);

        emptyListAirports = new ArrayList<>();

        mapper = new ObjectMapper();

        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(mainPageController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenSearchAirportByNamePart_thenReturnListAirportsIfExist() throws Exception {
        when(airportService.findAirportsByNamePart(airportNamePart)).thenReturn(airports);

        MockHttpServletResponse response = mvc.perform(
                post("/searchAirport")
                        .content(mapper.writeValueAsString(airportWithNamePart))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(airports), response.getContentAsString());
    }

    @Test
    public void whenSearchAirportByNamePart_thenReturnEmptyListAirportsIfNotExist()throws Exception{
        when(airportService.findAirportsByNamePart(notExistAirportNamePart)).thenReturn(emptyListAirports);

        MockHttpServletResponse response = mvc.perform(
                post("/searchAirport")
                        .content(mapper.writeValueAsString(notExistAirportWithNamePart))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(emptyListAirports), response.getContentAsString());
    }
}