package com.foxminded.airline.domain.service;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "planeId", unique = true, nullable = false)
    private int id;

    private String name;

    private int capacity;

    @OneToMany(mappedBy = "plane")
    @Transient
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
