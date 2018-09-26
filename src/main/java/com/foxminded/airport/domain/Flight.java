package com.foxminded.airport.domain;

import java.time.LocalDate;
import java.util.List;

public class Flight {
    private int id;
    private String number;
    private LocalDate date;
    private Plane plane;
    private List<Ticket> tickets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void add(Ticket ticket){
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket){
        tickets.remove(ticket);
    }
}
