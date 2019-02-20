package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Flight;

import java.util.List;

public interface FlightService {
    Flight findFlightByNumberAndDateAndTime(String number, String dateString, String timeString);

    List<Flight> findFlightsByDepartureAirportAndArrivalAirportAndDate(String dateString, String departureAirportName, String arrivalAirportName);

    List<Flight> findFlightsForAirportByDate(String dateString, String airportName);

    boolean isFlightEnabled(Flight flight);
}
