package com.foxminded.airline.domain.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlightTest {
    private Flight flight;
    private Ticket ticket;
    private FlightPrice flightPrice;

    private List<Ticket> storedTickets;
    private List<FlightPrice> storedFlightPrices;
    private List<Ticket> emptyStoredTickets;
    private List<FlightPrice> emptyStoredFlightPrices;

    @Before
    public void setUp() throws Exception {
        flight = new Flight();

        ticket = new Ticket();
        ticket.setNumber(1);

        flightPrice = new FlightPrice();
        flightPrice.setId(1);

        flight.add(ticket);
        flight.add(flightPrice);

        storedTickets = new ArrayList<>();
        storedTickets.add(ticket);

        storedFlightPrices = new ArrayList<>();
        storedFlightPrices.add(flightPrice);

        emptyStoredTickets = new ArrayList<>();
        emptyStoredFlightPrices = new ArrayList<>();
    }

    @Test
    public void shouldAddTicket_WhenAddTicket() {
        List<Ticket> expectedStoredTickets = storedTickets;
        List<Ticket> actualStoredTicket = flight.getTickets();

        assertEquals(expectedStoredTickets, actualStoredTicket);
    }

    @Test
    public void shouldRemoveTicket_WhenRemoveTicket() {
        flight.remove(ticket);

        List<Ticket> expectedStoredTickets = emptyStoredTickets;
        List<Ticket> actualStoredTicket = flight.getTickets();

        assertEquals(expectedStoredTickets, actualStoredTicket);
    }

    @Test
    public void shouldAddFlightPrice_WhenAddFlightPrice() {
        List<FlightPrice> expectedStoredFlightPrices = storedFlightPrices;
        List<FlightPrice> actualStoredFlightPrice = flight.getFlightPrices();

        assertEquals(expectedStoredFlightPrices, actualStoredFlightPrice);
    }

    @Test
    public void shouldRemoveFlightPrice_WhenRemoveFlightPrice() {
        flight.remove(flightPrice);

        List<FlightPrice> expectedStoredFlightPrices = emptyStoredFlightPrices;
        List<FlightPrice> actualStoredFlightPrice = flight.getFlightPrices();

        assertEquals(expectedStoredFlightPrices, actualStoredFlightPrice);
    }
}