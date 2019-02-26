package com.foxminded.airline.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.airline.domain.entity.Airport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainPageControllerIntegrationTest {
    @Autowired
    private MainPageController mainPageController;

    private MockMvc mvc;

    private List<Airport> airports;
    private Airport airportWithNamePart;
    private Airport notExistAirportWithNamePart;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        Airport airport = new Airport();
        airport.setId((long) 1);
        airport.setName("London, airport Heathrow");

        String airportNamePart = "London";
        String notExistAirportNamePart = "Moscow";

        airportWithNamePart = new Airport();
        airportWithNamePart.setName(airportNamePart);

        notExistAirportWithNamePart = new Airport();
        notExistAirportWithNamePart.setName(notExistAirportNamePart);

        airports = new ArrayList<>();
        airports.add(airport);

        mapper = new ObjectMapper();
        mvc = MockMvcBuilders.standaloneSetup(mainPageController).build();
    }

    @Test
    public void whenSearchAirportByNamePart_thenReturnListAirportsIfExist() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/airports")
                        .content(airportWithNamePart.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(airports), response.getContentAsString());
    }

    @Test
    public void whenSearchAirportByNamePart_thenReturnEmptyListAirportsIfNotExist() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/airports")
                        .content(notExistAirportWithNamePart.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(Collections.emptyList()), response.getContentAsString());
    }
}