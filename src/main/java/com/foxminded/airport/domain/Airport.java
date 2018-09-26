package com.foxminded.airport.domain;

import java.util.List;

public class Airport {
    private int id;
    private String name;
    private List<Flight> departureFlights;
    private List<Flight> arrivalFlights;

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

    public List<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public void setDepartureFlights(List<Flight> departureFlights) {
        this.departureFlights = departureFlights;
    }

    public List<Flight> getArrivalFlights() {
        return arrivalFlights;
    }

    public void setArrivalFlights(List<Flight> arrivalFlights) {
        this.arrivalFlights = arrivalFlights;
    }

    public void addDepartureFlight(Flight departureFlight){
        departureFlights.add(departureFlight);
    }

    public void removeDepartureFlight(Flight departureFlight){
        departureFlights.remove(departureFlight);
    }

    public void addArrivalFlight(Flight arrivalFlight){
        arrivalFlights.add(arrivalFlight);
    }

    public void removeArrivalFlight(Flight arrivalFlight){
        arrivalFlights.remove(arrivalFlight);
    }
}
