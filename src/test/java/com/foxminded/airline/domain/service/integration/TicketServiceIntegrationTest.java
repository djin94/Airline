package com.foxminded.airline.domain.service.integration;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.domain.entity.Ticket;
import com.foxminded.airline.domain.entity.User;
import com.foxminded.airline.domain.service.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketServiceIntegrationTest {
    @Autowired
    private TicketService ticketService;

    private Flight flight;
    private Ticket ticket;
    private User user;
    private List<Ticket> flightTickets;

    @Before
    public void setUp() throws Exception {
        flight = new Flight();
        flight.setId((long) 1);

        user = new User();
        user.setId((long) 1);

        ticket = new Ticket();
        ticket.setNumber((long) 1);
        ticket.setFlight(flight);
        ticket.setUser(user);

        flightTickets = new ArrayList<>();
        flightTickets.add(ticket);
    }

    @Test
    public void whenFindTicketsByFlight_thenReturnTickets() {
        List<Ticket> expectedTickets = flightTickets;
        List<Ticket> actualTickets = ticketService.findTicketsByFlight(flight);

        assertThat(actualTickets, hasItem(ticket));
    }

    @Test
    public void whenFindTicketsByUser_thenReturnTicketsForUser() {
        List<Ticket> expectedTickets = flightTickets;
        List<Ticket> actualTickets = ticketService.findTicketsByUser(user);

        assertEquals(expectedTickets, actualTickets);
    }
}