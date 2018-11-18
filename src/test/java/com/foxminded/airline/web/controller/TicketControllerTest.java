package com.foxminded.airline.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.FlightPrice;
import com.foxminded.airline.domain.entity.LevelTicket;
import com.foxminded.airline.domain.service.FlightService;
import com.foxminded.airline.domain.service.impl.FlightServiceImpl;
import com.foxminded.airline.domain.service.impl.SitServiceImpl;
import com.foxminded.airline.domain.service.impl.TicketServiceImpl;
import com.foxminded.airline.domain.service.impl.UserServiceImpl;
import com.foxminded.airline.domain.service.utils.FlightPriceConverter;
import com.foxminded.airline.domain.service.utils.TicketConverter;
import com.foxminded.airline.web.dto.FlightPriceDTO;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TicketControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private FlightServiceImpl flightService;

    @Mock
    private TicketServiceImpl ticketService;

    @Mock
    private TicketConverter ticketConverter;

    @Mock
    private FlightPriceConverter flightPriceConverter;

    @Mock
    private SitServiceImpl sitService;

    @Mock
    private UserServiceImpl userService;

    private Flight flight;
    private String number;
    private String dateString;
    private String timeString;
    private FlightPrice flightPrice;
    private FlightPriceDTO flightPriceDTO;
    private List<FlightPriceDTO> flightPriceDTOS;

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        flightPrice = new FlightPrice();
        flightPrice.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());

        number = "1574";
        dateString = "2018-11-11";
        timeString = "8:00";

        flight = new Flight();
        flight.setId(1);
        flight.setNumber(number);
        flight.setDate(LocalDate.parse(dateString));
        flight.setTime(LocalTime.of(8, 0));

        flight.add(flightPrice);

        flightPriceDTO = new FlightPriceDTO();
        flightPriceDTO.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());

        flightPriceDTOS = new ArrayList<>();
        flightPriceDTOS.add(flightPriceDTO);

        mapper = new ObjectMapper();

        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(ticketController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetFlightPrices_thenReturnFlightPrices() throws Exception {
        when(flightService.findFlightByNumberAndDateAndTime(number, dateString, timeString)).thenReturn(flight);
        when(flightPriceConverter.createDTOsForFlightPrices(flight.getFlightPrices())).thenReturn(flightPriceDTOS);

        mvc.perform(get("/buyticket")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                get("/buyticket/flightprices")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(flightPriceDTOS), response.getContentAsString());
    }
}