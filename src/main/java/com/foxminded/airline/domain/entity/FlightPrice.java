package com.foxminded.airline.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "flightprice")
public class FlightPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flightpriceId", unique = true)
    private Long id;

    private int price;

    @Column(name = "levelticket")
    private String levelTicket;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "flight_id")
    @JsonIgnore
    private Flight flight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getLevelTicket() {
        return levelTicket;
    }

    public void setLevelTicket(String levelTicket) {
        this.levelTicket = levelTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightPrice that = (FlightPrice) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FlightPrice{" +
                "id=" + id +
                ", price=" + price +
                ", levelTicket='" + levelTicket + '\'' +
                '}';
    }
}
