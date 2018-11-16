package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.impl.TicketServiceImpl;
import com.foxminded.airline.dao.repository.TicketRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TicketServiceTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketRepository ticketRepository;

    private Ticket ticket;
    private Flight flight;

    private User user;
    private List<Ticket> flightTickets;

    @Before
    public void setUp() throws Exception {
        flight = new Flight();

        user = new User();

        ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setUser(user);

        flightTickets = new ArrayList<>();
        flightTickets.add(ticket);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenFindTicketsByFlight_thenReturnTickets() {
        when(ticketRepository.findByFlight(flight)).thenReturn(flightTickets);

        List<Ticket> expectedTickets = flightTickets;
        List<Ticket> actualTickets = ticketService.findTicketsByFlight(flight);

        assertEquals(expectedTickets, actualTickets);
    }

    @Test
    public void whenFindTicketsByUser_thenReturnTicketsForUser() {
        when(ticketRepository.findByUser(user)).thenReturn(flightTickets);

        List<Ticket> expectedTickets = flightTickets;
        List<Ticket> actualTickets = ticketService.findTicketsByUser(user);

        assertEquals(expectedTickets, actualTickets);
    }
}