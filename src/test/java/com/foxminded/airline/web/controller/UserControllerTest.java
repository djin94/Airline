package com.foxminded.airline.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.TicketService;
import com.foxminded.airline.domain.service.UserService;
import com.foxminded.airline.domain.service.utils.TicketConverter;
import com.foxminded.airline.domain.service.utils.UserConverter;
import com.foxminded.airline.web.dto.TicketDTO;
import com.foxminded.airline.web.dto.UserDTO;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    @Mock
    private TicketConverter ticketConverter;

    private User user;
    private UserDTO userDTO;
    private TicketDTO ticketDTO;
    private Ticket ticket;
    private List<Ticket> tickets;
    private List<TicketDTO> ticketDTOS;


    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        userDTO = new UserDTO();
        userDTO.setLogin("Kabatov");

        user = new User();
        user.setId((long) 1);

        user.setLogin("Kabatov");

        ticketDTO = new TicketDTO();
        ticketDTOS = new ArrayList<>();
        ticketDTOS.add(ticketDTO);

        ticket = new Ticket();
        ticket.setUser(user);

        tickets = new ArrayList<>();
        tickets.add(ticket);

        mapper = new ObjectMapper();

        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetCurrentUserName_thenReturnCurrentUserName() throws Exception {
        when(userService.getCurrentUser()).thenReturn(Optional.of(user));
        when(userConverter.createUserDTOFromUser(user)).thenReturn(userDTO);

        mvc.perform(get("/user")
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                get("/user/userlogin")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(userDTO), response.getContentAsString());
    }

    @Test
    public void whenEditPassengerData_thenEditPassenger() throws Exception {
        when(userService.getCurrentUser()).thenReturn(Optional.of(user));

        mvc.perform(get("/user")
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                put("/user/passenger")
                        .content(mapper.writeValueAsString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void whenGetCurrentUserHistoryOfOrders_thenReturnCurrentUserHistoryOfOrders() throws Exception {
        when(userService.getCurrentUser()).thenReturn(Optional.of(user));
        when(ticketService.findTicketsByUser(user)).thenReturn(tickets);
        when(ticketConverter.createTicketDTOsFromTickets(tickets)).thenReturn(ticketDTOS);

        mvc.perform(get("/user")
                .contentType(MediaType.TEXT_HTML_VALUE))
                .andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                get("/user/history/currenthistory")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(ticketDTOS), response.getContentAsString());
    }
}