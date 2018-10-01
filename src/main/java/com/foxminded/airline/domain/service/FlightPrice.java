package com.foxminded.airline.domain.service;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flightprice")
public class FlightPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "flightpriceId", unique = true, nullable = false)
    private int id;

    private String level;

    private int price;

    @OneToMany(mappedBy = "flightPrice")
    @Transient
    private List<Ticket> tickets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
