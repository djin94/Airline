package com.foxminded.airline.utils;

import com.foxminded.airline.domain.entity.Flight;
import com.foxminded.airline.dto.FlightDTO;

import java.util.ArrayList;
import java.util.List;

public class FlightConverter {
    public List<FlightDTO> createDTOsForFlights(List<Flight> flights) {
        List<FlightDTO> flightDTOS = new ArrayList<>();
        flights.stream()
                .forEach(flight -> {
                    FlightDTO flightDTO = new FlightDTO();
                    flightDTO.setNumber(flight.getNumber());
                    flightDTO.setPlaneName(flight.getPlane().getName());
                    flightDTO.setDateString(flight.getDate().toString());
                    flightDTO.setTimeString(flight.getTime().toString());
                    flightDTO.setDepartureAirport(flight.getDepartureAirport().getName());
                    flightDTO.setArrivalAirport(flight.getArrivalAirport().getName());
                    flightDTOS.add(flightDTO);
                });
        return flightDTOS;
    }
}
