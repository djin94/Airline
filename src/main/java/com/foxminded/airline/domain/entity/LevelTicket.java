package com.foxminded.airline.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "levelticket")
public class LevelTicket {

    @Id
    @SequenceGenerator(name = "levelticket_id_seq", sequenceName = "levelticket_levelticket_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "levelticket_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "levelticketId", unique = true)
    private int id;

    private String level;

    @OneToMany(mappedBy = "levelTicket", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonIgnore
    private List<Sit> sit = new ArrayList<>();

    @OneToMany(mappedBy = "levelTicket", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JsonIgnore
    private List<FlightPrice> flightPrices = new ArrayList<>();

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

    public List<Sit> getSit() {
        return sit;
    }

    public void setSit(List<Sit> sit) {
        this.sit = sit;
    }

    public List<FlightPrice> getFlightPrices() {
        return flightPrices;
    }

    public void setFlightPrices(List<FlightPrice> flightPrices) {
        this.flightPrices = flightPrices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LevelTicket that = (LevelTicket) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "LevelTicket{" +
                "level='" + level + '\'' +
                '}';
    }
}
