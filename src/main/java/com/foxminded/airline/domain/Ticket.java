package com.foxminded.airline.domain;

import javax.persistence.*;

@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@Table(name = "Ticket")
public class Ticket {
    private long number;
    private FlightPrice flightPrice;
    private String place;
    private Passenger passenger;

    @Id
    @Column(name = "number", unique = true, nullable = false)
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @ManyToOne
    @JoinColumn(name = "flightprice_id")
    public FlightPrice getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(FlightPrice flightPrice) {
        this.flightPrice = flightPrice;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
