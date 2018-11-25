package com.foxminded.airline.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.airline.domain.entity.*;
import com.foxminded.airline.web.dto.FlightDTO;
import com.foxminded.airline.web.dto.FlightPriceDTO;
import com.foxminded.airline.web.dto.TicketDTO;
import com.foxminded.airline.web.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketControllerIntegrationTest {
    @Autowired
    private TicketController ticketController;

    private MockMvc mvc;

    private String number;
    private String dateString;
    private String timeString;
    private Sit sit;
    private Sit availableSit;
    private FlightPriceDTO flightPriceDTO;
    private TicketDTO ticketDTO;

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        FlightPrice flightPrice = new FlightPrice();
        flightPrice.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());

        Plane plane = new Plane();
        plane.setId((long) 1);
        plane.setName("Boeing 747");
        plane.setCapacity(500);

        sit = new Sit();
        sit.setId((long) 1);
        sit.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());
        sit.setPlace("12A");
        sit.setPlane(plane);

        availableSit = new Sit();
        availableSit.setId((long) 2);
        availableSit.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());
        availableSit.setPlace("12C");
        availableSit.setPlane(plane);

        number = "1574";
        dateString = "2018-10-01";
        timeString = "08:05";

        Flight flight = new Flight();
        flight.setId((long) 1);
        flight.setNumber(number);
        flight.setDate(LocalDate.parse(dateString));
        flight.setTime(LocalTime.of(8, 5));
        flight.add(flightPrice);

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setNumber(flight.getNumber());
        flightDTO.setDateString(flight.getDate().toString());
        flightDTO.setTimeString(flight.getTime().toString());
        flightDTO.setPlaneName("Boeing 747");
        flightDTO.setDepartureAirport("London, airport Heathrow");
        flightDTO.setArrivalAirport("Stockholm, airport Arlanda");

        flightPriceDTO = new FlightPriceDTO();
        flightPriceDTO.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());
        flightPriceDTO.setPrice("100.0");

        User user = new User();
        user.setId((long) 1);
        user.setLogin("Chir");
        user.setFirstName("Alexander");
        user.setPatronym("Ivanovich");
        user.setLastName("Chirkin");
        user.setPassportNumber("48524788932");

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setPatronym(user.getPatronym());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassportNumber(user.getPassportNumber());

        ticketDTO = new TicketDTO();
        ticketDTO.setSit(sit.getPlace());
        ticketDTO.setUserDTO(userDTO);
        ticketDTO.setFlightDTO(flightDTO);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setSit(sit);
        ticket.setFlight(flight);

        mapper = new ObjectMapper();

        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    public void whenGetFlightPrices_thenReturnFlightPrices() throws Exception {
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

        TypeReference<List<FlightPriceDTO>> mapType = new TypeReference<List<FlightPriceDTO>>() {
        };
        List<FlightPriceDTO> actualFlightPriceDTOS = mapper.readValue(response.getContentAsString(), mapType);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(actualFlightPriceDTOS, hasItem(flightPriceDTO));
    }

    @Test
    public void whenGetAvailableSitsForFlight_thenReturnSits() throws Exception {
        mvc.perform(get("/buyticket")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                get("/buyticket/sits")
                        .content(mapper.writeValueAsString(sit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        TypeReference<List<Sit>> mapType = new TypeReference<List<Sit>>() {
        };
        List<Sit> actualSits = mapper.readValue(response.getContentAsString(), mapType);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(actualSits, hasItem(availableSit));
    }

    @Test
    public void whenCreateTicket_thenCreateTicket() throws Exception {
        mvc.perform(get("/buyticket")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                post("/buyticket")
                        .content(mapper.writeValueAsString(ticketDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @WithMockUser(username = "djin94")
    public void whenCreateTicketForUser_thenCreateTicketForUser() throws Exception {
        mvc.perform(get("/buyticket")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                post("/user/buyticket")
                        .content(mapper.writeValueAsString(ticketDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void whenGetListTicketsForFlight_thenReturnListTicketsForFlight() throws Exception {
        mvc.perform(get("/buyticket")
                .param("number", number)
                .param("dateString", dateString)
                .param("timeString", timeString)
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                post("/admin/listtickets")
                        .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        TypeReference<List<TicketDTO>> mapType = new TypeReference<List<TicketDTO>>() {
        };
        List<TicketDTO> actualTicketDTOS = mapper.readValue(response.getContentAsString(), mapType);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertThat(actualTicketDTOS, hasItem(ticketDTO));
    }
}