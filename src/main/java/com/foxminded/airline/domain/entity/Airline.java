package com.foxminded.airline.domain.entity;

import java.util.List;

public class Airline {
    private String name;
    private List<Flight> flights;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void add(Flight flight) {
        flights.add(flight);
    }

    public void remove(Flight flight) {
        flights.remove(flight);
    }
}
