package com.foxminded.airline.domain.service;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.dto.FlightDTO;

import java.util.List;

public interface FlightService {
    FlightDTO createFlightDTO(String nameDepartureAirport, String nameArrivalAirport, String date);

    List<Flight> findFlightByFlightDTO(FlightDTO flightDTO);

    List<FlightDTO> createDTOsForFlights(List<Flight> flights);
}
