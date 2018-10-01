package com.foxminded.airline.domain.service;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Airline")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airlineId", unique = true, nullable = false)
    private int id;
    private String name;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
