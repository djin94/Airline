package com.foxminded.airline.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.airline.dao.repository.UserRepository;
import com.foxminded.airline.domain.entity.*;
import com.foxminded.airline.web.dto.FlightDTO;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerIntegrationTest {
    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mvc;

    private User user;
    private UserDTO userDTO;
    private UserDTO anotherUserDTO;
    private List<TicketDTO> ticketDTOS;

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        Plane plane = new Plane();
        plane.setId((long) 1);
        plane.setName("Boeing 747");
        plane.setCapacity(500);

        Sit sit = new Sit();
        sit.setId((long) 1);
        sit.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());
        sit.setPlace("12A");
        sit.setPlane(plane);

        String number = "1574";
        String dateString = "2018-10-01";

        FlightPrice flightPrice = new FlightPrice();
        flightPrice.setLevelTicket(LevelTicket.ECONOM.getLevelTicket());

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

        user = new User();
        user.setId((long) 1);
        user.setLogin("Chir");
        user.setFirstName("Alexander");
        user.setPatronym("Ivanovich");
        user.setLastName("Chirkin");
        user.setPassportNumber("48524788932");

        userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setPatronym(user.getPatronym());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassportNumber(user.getPassportNumber());

        anotherUserDTO = new UserDTO();
        anotherUserDTO.setFirstName("Mikhail");
        anotherUserDTO.setPatronym("Ivanovich");
        anotherUserDTO.setLastName("Lovich");
        anotherUserDTO.setPassportNumber("455687412");

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setSit(sit.getPlace());
        ticketDTO.setUserDTO(userDTO);
        ticketDTO.setFlightDTO(flightDTO);

        ticketDTOS = new ArrayList<>();
        ticketDTOS.add(ticketDTO);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setSit(sit);
        ticket.setFlight(flight);

        mapper = new ObjectMapper();

        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @WithMockUser(value = "Chir")
    public void whenGetCurrentUserName_thenReturnCurrentUserName() throws Exception {
        mvc.perform(get("/user")
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/user/userlogin")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(userDTO), response.getContentAsString());
    }

    @Test
    @WithMockUser(value = "Chir")
    public void whenEditPassengerData_thenEditPassenger() throws Exception {
        mvc.perform(get("/user")
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                put("/api/v1/user/passenger")
                        .content(mapper.writeValueAsString(anotherUserDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        userRepository.save(user);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @WithMockUser(value = "Chir")
    public void whenGetCurrentUserHistoryOfOrders_thenReturnCurrentUserHistoryOfOrders() throws Exception {
        mvc.perform(get("/user")
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                get("/api/v1/user/history/currenthistory")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(ticketDTOS), response.getContentAsString());
    }
}