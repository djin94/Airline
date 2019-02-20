package com.foxminded.airline.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.airline.domain.entity.*;
import com.foxminded.airline.domain.service.impl.FlightServiceImpl;
import com.foxminded.airline.domain.service.impl.SitServiceImpl;
import com.foxminded.airline.domain.service.impl.TicketServiceImpl;
import com.foxminded.airline.domain.service.impl.UserServiceImpl;
import com.foxminded.airline.domain.service.utils.FlightPriceConverter;
import com.foxminded.airline.domain.service.utils.TicketConverter;
import com.foxminded.airline.web.dto.FlightPriceDTO;
import com.foxminded.airline.web.dto.TicketDTO;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
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
    private Sit sit;
    private List<Sit> sits;
    private List<FlightPriceDTO> flightPriceDTOS;
    private TicketDTO ticketDTO;
    private Ticket ticket;
    private List<Ticket> tickets;
    private List<TicketDTO> ticketDTOS;
    private User user;

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        FlightPrice flightPrice = new FlightPrice();
        flightPrice.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());

        sit = new Sit();
        sit.setId((long) 1);
        sit.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());
        sit.setPlace("12B");

        number = "1574";
        dateString = "2018-11-11";
        timeString = "8:00";

        flight = new Flight();
        flight.setId((long) 1);
        flight.setNumber(number);
        flight.setDate(LocalDate.parse(dateString));
        flight.setTime(LocalTime.of(8, 0));

        flight.add(flightPrice);

        FlightPriceDTO flightPriceDTO = new FlightPriceDTO();
        flightPriceDTO.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());

        flightPriceDTOS = new ArrayList<>();
        flightPriceDTOS.add(flightPriceDTO);

        sits = new ArrayList<>();
        sits.add(sit);

        user = new User();
        user.setId((long) 1);

        ticketDTO = new TicketDTO();
        ticketDTOS = new ArrayList<>();
        ticketDTOS.add(ticketDTO);

        ticket = new Ticket();
        ticket.setUser(user);
        ticket.setSit(sit);
        ticket.setFlight(flight);

        tickets = new ArrayList<>();
        tickets.add(ticket);

        mapper = new ObjectMapper();

        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(ticketController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetFlightPrices_thenReturnFlightPrices() throws Exception {
        when(flightService.findFlightByNumberAndDateAndTime(number, dateString, timeString)).thenReturn(flight);
        when(flightPriceConverter.createDTOsForFlightPrices(flight.getFlightPrices())).thenReturn(flightPriceDTOS);

        mvc.perform(get("/tickets")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/tickets/flightprices")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(flightPriceDTOS), response.getContentAsString());
    }

    @Test
    public void whenGetAvailableSitsForFlight_thenReturnSits() throws Exception {
        when(flightService.findFlightByNumberAndDateAndTime(number, dateString, timeString)).thenReturn(flight);
        when(sitService.getLevelTicketFromSitOrDefault(sit)).thenReturn(sit.getLevelTicket());
        when(sitService.findAvailableSitsForFlightAndLevelTicket(flight, sit.getLevelTicket())).thenReturn(sits);

        mvc.perform(get("/tickets")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/tickets/sits")
                        .content(mapper.writeValueAsString(sit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(sits), response.getContentAsString());
    }

    @Test
    public void whenCreateTicket_thenCreateTicket() throws Exception {
        when(flightService.findFlightByNumberAndDateAndTime(number, dateString, timeString)).thenReturn(flight);
        when(ticketConverter.createTicketFromDTO(ticketDTO, flight)).thenReturn(ticket);

        mvc.perform(get("/tickets")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/tickets")
                        .content(mapper.writeValueAsString(ticketDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void whenCreateTicketForUser_thenCreateTicketForUser() throws Exception {
        when(flightService.findFlightByNumberAndDateAndTime(number, dateString, timeString)).thenReturn(flight);
        when(userService.getCurrentUser()).thenReturn(Optional.of(user));
        when(ticketConverter.createTicketFromDTOForUser(ticketDTO, flight, user)).thenReturn(ticket);

        mvc.perform(get("/tickets")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/user/tickets")
                        .content(mapper.writeValueAsString(ticketDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void whenGetListTicketsForFlight_thenReturnListTicketsForFlight() throws Exception {
        when(flightService.findFlightByNumberAndDateAndTime(number, dateString, timeString)).thenReturn(flight);
        when(ticketService.findTicketsByFlight(flight)).thenReturn(tickets);
        when(ticketConverter.createTicketDTOsFromTickets(tickets)).thenReturn(ticketDTOS);

        mvc.perform(get("/tickets")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/admin/listtickets")
                        .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(ticketDTOS), response.getContentAsString());
    }
}